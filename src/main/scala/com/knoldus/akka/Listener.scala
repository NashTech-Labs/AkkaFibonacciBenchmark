package com.knoldus.akka

import akka.actor.Actor.actorOf
import akka.actor.Actor
import akka.actor.ActorRef
import akka.routing.SmallestMailboxFirstIterator
import akka.routing.Routing

class Listener(numberOfWorkers: Int, diagnostics: ActorRef) extends Actor {
  var router: ActorRef = _
  override def preStart = {
    val workers = Vector.fill(numberOfWorkers)(actorOf(new Processor(diagnostics)).start)
    router = Routing.loadBalancerActor(SmallestMailboxFirstIterator(workers)).start
  }

  def receive = {
    case msg: Int => router ! msg
  }
  
   def randomizeInteger(start: Int, end: Int): Int = {
    val rnd = new scala.util.Random
    val range = start to end
    val random = range(rnd.nextInt(range length))
    random
  }
   
}


