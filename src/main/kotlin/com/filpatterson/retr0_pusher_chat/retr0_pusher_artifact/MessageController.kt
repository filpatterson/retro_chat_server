package com.filpatterson.retr0_pusher_chat.retr0_pusher_artifact

import Message
import com.pusher.rest.Pusher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//  constants for the Pusher-API
private const val PUSHER_APP_ID = "974671"
private const val PUSHER_APP_KEY = "8b63e6c6448bbe805172"
private const val PUSHER_APP_SECRET = "30ef11caf55aac4c9c6d"
private const val PUSHER_APP_CLUSTER = "eu"

@RestController
@RequestMapping("/message")
class MessageController {

    //  init if the pusher for getting messages and their further sending
    private val pusher = Pusher(PUSHER_APP_ID, PUSHER_APP_KEY, PUSHER_APP_SECRET)

    //  set cluster
    init { pusher.setCluster(PUSHER_APP_CLUSTER) }

    //  posting of the incoming message
    @PostMapping
    fun postMessage(@RequestBody message: Message) : ResponseEntity<Unit> {
        pusher.trigger("chat", "new_message", message)
        return ResponseEntity.ok().build()
    }
}