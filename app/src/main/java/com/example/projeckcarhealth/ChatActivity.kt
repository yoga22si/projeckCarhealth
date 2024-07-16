package com.example.projeckcarhealth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var buttonSend: Button
    private lateinit var chatMessagesContainer: LinearLayout
    private lateinit var scrollView: ScrollView
    private lateinit var backButton: ImageButton
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    private var chatSessionId: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageInput = findViewById(R.id.messageInput)
        buttonSend = findViewById(R.id.buttonSend)
        chatMessagesContainer = findViewById(R.id.chatMessagesContainer)
        scrollView = findViewById(R.id.scrollView)
        backButton = findViewById(R.id.backButton)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("chats")
        firestore = FirebaseFirestore.getInstance()

        // Mendapatkan chat session ID dari Intent
        chatSessionId = intent.getStringExtra("CHAT_SESSION_ID")

        buttonSend.setOnClickListener {
            sendMessage()
        }

        backButton.setOnClickListener {
            onBackPressed()  // Navigate back to the previous screen
        }

        loadMessages()
    }

    private fun sendMessage() {
        val message = messageInput.text.toString()
        if (message.isNotEmpty()) {
            val userId = auth.currentUser?.uid ?: ""
            val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
            val messageId = database.child(chatSessionId ?: "").push().key

            firestore.collection("users").document(userId).get().addOnSuccessListener { document ->
                val username = document.getString("username") ?: "Unknown"

                if (messageId != null) {
                    val messageMap = mapOf(
                        "sender" to userId,
                        "message" to message,
                        "timestamp" to timestamp,
                        "username" to username
                    )

                    database.child(chatSessionId ?: "").child("messages").child(messageId).setValue(messageMap)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                messageInput.text.clear()
                                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
                            } else {
                                Toast.makeText(applicationContext, "Failed to send message", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }.addOnFailureListener { e ->
                Log.e("ChatActivity", "Failed to get username", e)
                Toast.makeText(applicationContext, "Failed to get username: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadMessages() {
        database.child(chatSessionId ?: "").child("messages").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                try {
                    val messageMap = snapshot.value as Map<*, *>
                    val message = messageMap["message"] as String
                    val sender = messageMap["sender"] as String
                    val username = messageMap["username"] as String
                    addMessageToUI(sender, username, message)
                } catch (e: Exception) {
                    Log.e("ChatActivity", "Error processing message", e)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {
                Log.e("ChatActivity", "Failed to load messages", error.toException())
                Toast.makeText(applicationContext, "Failed to load messages: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addMessageToUI(sender: String, username: String, message: String) {
        val isCurrentUser = sender == auth.currentUser?.uid
        val layoutId = if (isCurrentUser) R.layout.item_message_self else R.layout.item_message_other
        val view = LayoutInflater.from(this).inflate(layoutId, chatMessagesContainer, false)

        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val usernameTextView: TextView = view.findViewById(R.id.usernameTextView)

        messageTextView.text = message
        usernameTextView.text = if (isCurrentUser) "You" else username

        chatMessagesContainer.addView(view)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }
}
