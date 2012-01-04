package com.knoldus.akka

import akka.actor.Actor

class Diagnostics(numberOfMessages: Int, startTime: Long) extends Actor {
  var messagesReceived = 0

  def receive = {
    case msg =>
      messagesReceived = messagesReceived + 1
      if (messagesReceived == numberOfMessages) {
        val timeElapsed = System.currentTimeMillis - startTime
        println("Total Time: " + timeElapsed)
        println("Throughput: " + ((numberOfMessages * 1000) / timeElapsed))
      }
  }
}