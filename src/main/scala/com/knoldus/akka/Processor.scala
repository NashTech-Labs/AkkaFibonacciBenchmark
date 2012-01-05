package com.knoldus.akka

import akka.actor.Actor
import akka.actor.ActorRef
import akka.dispatch.Dispatchers
import akka.dispatch.UnboundedMailbox

object Processor {
  val dispatcher = Dispatchers.newExecutorBasedEventDrivenDispatcher("multiplier", 100, UnboundedMailbox())
                  .setCorePoolSizeFromFactor(1.0)
                  .setMaxPoolSizeFromFactor(1.0)
                  .build
}

class Processor(diagnostics: ActorRef) extends Actor {
  self.dispatcher = Processor.dispatcher
  def receive = {
    case num: Int =>
      val fibonacci = fib(num)
      //println("Fibonacci of " + num + " is " + fibonacci)
      diagnostics ! fibonacci
  }

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n - 1) + fib(n - 2)
  }
}