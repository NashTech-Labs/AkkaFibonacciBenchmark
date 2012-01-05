package com.knoldus.akka

import akka.actor.Actor.actorOf
import akka.actor.Actor
import akka.actor.ActorRef
import akka.routing.CyclicIterator
import akka.routing.Routing

object FibonacciBench extends App {
  val NO_OF_MESSAGES = 1000000
  val NO_OF_WORKERS = 8

  val listener = initializeListener(NO_OF_WORKERS, NO_OF_MESSAGES)
  tailRecurseAndFireMessages(NO_OF_MESSAGES)

  def initializeListener(numberOfWorkers: Int, totalMessages: Int): ActorRef = {
    val diagnostics = actorOf(new Diagnostics(totalMessages, System.currentTimeMillis)).start
    actorOf(new Listener(numberOfWorkers, diagnostics)).start
  }

  def tailRecurseAndFireMessages(totalMessages: Int) {
    if (totalMessages > 0) {
      listener ! 20
      tailRecurseAndFireMessages(totalMessages - 1)
    }
  }

}