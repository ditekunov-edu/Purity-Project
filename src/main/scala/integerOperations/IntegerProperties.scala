package integerOperations


import utils.InputException
import scala.util.{Failure, Success, Try}
import utils.ExceptionMessages.NegativeInput

class IntegerProperties(val firstInt: Int) {

  import IntegerProperties._

  /**
    * Service function to generate lists
    */
  private def generateArithmeticRegression(until: Int, regressionList: List[Int] = List()): List[Int] =
    until match {
    case 0 => regressionList
    case _ => generateArithmeticRegression(until - 1, regressionList :+ until)
  }

  /**
    * Checks, whether the number is even
    */
  def isEven: Boolean = firstInt % 2 == 0

  /**
    * Checks, whether the number is odd
    */
  def isOdd: Boolean = firstInt % 2 != 0

  /**
    * Checks, whether the number is a square of two numbers
    */
  def isSquared(secondInt: Int = firstInt, multiplier: Int = 3): Boolean = {
    if (secondInt > 0) secondInt match {
      case 1 => true
      case 0 => false
      case _ => isSquared(secondInt - multiplier, multiplier + 2)
    }
    else if (firstInt == 0) true
    else false
  }

  /**
    * Finds the sum of number's digits.
    */
  def sumOfDigits: Int = Try(sumOfDigitsLogic()) match {
    case Success(something) => something
    case Failure(ex) => throw new InputException(ex.toString)
  }

  /**
    * Sub-function for sumOfDigits(), to provide errors handling.
    */
  private def sumOfDigitsLogic(cur: Int = firstInt, sum: Int = 0): Int = cur match {
    case 0 => sum
    case _ => sumOfDigitsLogic(cur / 10, sum + (cur % 10))
  }

  /**
    * Finds the composition of number's digits.
    */
  def compositionOfDigits: Int = Try(compositionOfDigitsLogic()) match {
    case Success(something) => something
    case Failure(ex) => throw new InputException(ex.toString)
  }

  /**
    * Sub-function for compositionOfDigits(), to provide errors handling.
    */
  private def compositionOfDigitsLogic(cur: Int = firstInt, comp: Int = 1): Int = cur match {
    case 0 => if (firstInt == 0) 0 else comp
    case _ => compositionOfDigitsLogic(cur / 10, comp * (cur % 10))
  }

  /**
    * Finds the number of digits in a number.
    */
  def numOfDigits: Int = Try(numOfDigitsLogic()) match {
    case Success(something) => something
    case Failure(ex) => throw new InputException(ex.toString)
  }

  /**
    * Sub-function for numOfDigits(), to provide errors handling.
    */
  private def numOfDigitsLogic(cur: Int = firstInt, comp: Int = 0): Int = cur match {
    case 0 => if (firstInt == 0) 1 else comp
    case _ => numOfDigitsLogic(cur / 10, comp + 1)
  }

  /**
    * Returns the list of all the divisors of a number.
    */
  def listDivisors: List[Int] = Try(listDivisorsLogic()) match {
    case Success(something) => something.sorted
    case Failure(ex) => throw new InputException(ex.toString)
  }

  /**
    * Sub-function for listDivisors(), to provide deep recursion handling.
    */
  private def listDivisorsLogic(divisorsList: List[Int] = List(firstInt), total: Int = 1): List[Int] = {
      if (total == firstInt / 2) divisorsList :+ total
      else if (total > firstInt / 2) divisorsList
      else if (firstInt % total == 0) listDivisorsLogic(divisorsList :+ total, total + 1)
      else listDivisorsLogic(divisorsList, total + 1)
  }

  /**
    * Finds the greatest divisor of a number.
    */
  def nthGreatestDivisor(nPosition: Int): Int = {
    if (nPosition < 0) throw new InputException("\"nthGreatestDivisor\" " + NegativeInput)
    else Try(firstInt.listDivisors.sortWith(_ > _)(nPosition)) match {
      case Success(something) => something
      case Failure(ex) => throw new InputException("\"nthGreatestDivisor\" got " + ex.toString)
    }

  }

  /**
    * Returns the list of all the binary divisors of a number.
    */
  def listBinaryDivisors: List[Int] = Try(listBinaryDivisorsLogic()) match {
    case Success(something) => something
    case Failure(ex) => throw new InputException("\"listBinaryDivisors\" got " + ex.toString)
  }

  /**
    * Sub-function for listBinaryDivisors(), to provide deep recursion handling.
    */
  private def listBinaryDivisorsLogic(divisorsList: List[Int] = List(), total: Int = 2): List[Int] = {
    if (total == firstInt) divisorsList :+ firstInt
    else if (total > firstInt) divisorsList
    else if (firstInt % total == 0) listBinaryDivisorsLogic(divisorsList :+ total, total * 2)
    else listBinaryDivisorsLogic(divisorsList, total * 2)
  }

  /**
    * Returns the list of all the n-multiply divisors of a number.
    */
  def listN_MultipleDivisors(n: Int): List[Int] = {
    if (n != 1)
    Try(listN_MultipleDivisorsLogic(n, total = n)) match {
      case Success(something) => something
      case Failure(ex) => throw new InputException("\"listN_MultipleDivisors\" got " + ex.toString)
    }
    else generateArithmeticRegression(firstInt).reverse
  }

  /**
    * Sub-function for listN_MultipleDivisors(), to provide deep recursion handling.
    */
  private def listN_MultipleDivisorsLogic(n: Int, divisorsList: List[Int] = List(), total: Int): List[Int] = {
    if (total == firstInt) divisorsList :+ firstInt
    else if (total > firstInt) divisorsList
    else if (firstInt % total == 0) listN_MultipleDivisorsLogic(n, divisorsList :+ total, total * n)
    else listN_MultipleDivisorsLogic(n, divisorsList, total * n)
  }

}

object IntegerProperties {
  implicit def intToIntegerProperties(a: Int): IntegerProperties = new IntegerProperties(a)
}
