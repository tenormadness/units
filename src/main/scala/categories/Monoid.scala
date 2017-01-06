package main.scala.categories

/**
  * Created by luca on 12/8/16.
  */
trait Monoid[T] {

  def zero: T
  def append(l:T, r:T): T

}

trait Summable[T] extends  Monoid[T] {

  def minus(l:T, r:T): T
  def plus(l:T, r:T): T = append(l, r)
  def unaryMinus(l: T): T

}

trait Vector[T] extends Summable[T] {

  def mul(l: T, r: Double): T
  def div(l: T, r: Double): T

}

trait Ring[T] extends Monoid[T] {

  def mul(l: T, r: T): T
  def div(l: T, r: T): T

}
