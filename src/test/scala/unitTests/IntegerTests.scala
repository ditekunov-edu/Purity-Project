package unitTests

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import integerOperations.IntegerProperties._
import integerOperations.IntegerGenerators._
import utils.InputException
import integerOperations.IntegerMath._

class IntegerTests extends FunSuite {

  final val testEvenList: List[Int] = List(2,6,222,-2, 10000000)
  final val testOddList: List[Int] = List(1,3,15,275,100000001)
  final val testSquaresList: List[Int] = List(4,9,625,1000000)
  final val emptyList = List()

  test("Testing of isEven") {
    testEvenList.foreach { cur: Int => cur.isEven shouldBe true }
    testOddList.foreach { cur: Int => cur.isEven shouldBe false }
  }

  test("Testing of isOdd") {
    testEvenList.foreach { cur: Int => cur.isOdd shouldBe false }
    testOddList.foreach { cur: Int => cur.isOdd shouldBe true }
  }

  test("Testing of isSquared") {
    testEvenList.foreach { cur: Int => cur.isSquared() shouldBe false }
    testSquaresList.foreach {cur: Int => cur.isSquared() shouldBe true}
  }

  test("Testing of sumOfDigits") {
    0.sumOfDigits shouldBe 0
    50.sumOfDigits shouldBe 5
    123456789.sumOfDigits shouldBe 45
  }

  test("Testing of compositionOfDigits") {
    0.compositionOfDigits shouldBe 0
    50.compositionOfDigits shouldBe 0
    999999999.compositionOfDigits shouldBe 387420489
  }

  test("Testing of numOfDigits") {
    0.numOfDigits shouldBe 1
    50.numOfDigits shouldBe 2
    999999999.numOfDigits shouldBe 9
  }

  test("Testing of nthGreatestDivisor") {
    100.nthGreatestDivisor(0) shouldBe 100
    100.nthGreatestDivisor(2) shouldBe 25
    assertThrows[InputException] {
      100.nthGreatestDivisor(100)
    }
  }

  test("Testing of numOfDivisors") {
    100.numOfDivisors shouldBe 9
    0.numOfDivisors shouldBe 1
  }

  test("Testing of sumOfDivisors") {
    100.sumOfDivisors shouldBe 217
    0.sumOfDivisors shouldBe 0
    assertThrows[InputException] {
      -1.sumOfDivisors
    }
  }

  test("Testing of isPrime") {
    10.isPrime shouldBe false
    17.isPrime shouldBe true
    289.isPrime shouldBe false
    assertThrows[InputException] {
      1.isPrime
    }
  }

  test("Testing of gcdWith") {
    14.gcdWith(7) shouldBe 7
    21.gcdWith(7) shouldBe 7
  }

  test("Testing of sqr") {
    0.sqr shouldBe 0
    7.sqr shouldBe 49
    -2.sqr shouldBe 4
  }

  test("Testing of powN") {
    0.powN(3) shouldBe 0
    10.powN(2) shouldBe 100
    assertThrows[InputException] {
      -2.powN(5)
    }
  }

  test("Testing of isPrimeFermat") {
    2.isPrimeFermat() shouldBe true
    17.isPrimeFermat(100) shouldBe true
    49.isPrimeFermat(200) shouldBe false
    13.isPrimeFermat(200) shouldBe true
  }

  test("Testing of isCarmichael") {
    561.isCarmichael shouldBe true
    8911.isCarmichael shouldBe true
    17777.isCarmichael shouldBe false
  }

  test("Testing of isLuc_Carmichael") {
    399.isLucas_Carmichael shouldBe true
    935.isLucas_Carmichael shouldBe true
    17777.isLucas_Carmichael shouldBe false
  }

  test("Testing of isFibonacci") {
    0.isFibonacci shouldBe true
    1.isFibonacci shouldBe true
    4.isFibonacci shouldBe false
    assertThrows[InputException] {
      (-1).isFibonacci
    }
  }

  test("Testing of nthCatalan") {
    7.nthCatalan shouldBe 429
  }

  test("Testing of binaryPower") {
    10.binaryPower(2) shouldBe 100
    -10.binaryPower(2) shouldBe 100
    -10.binaryPower(3) shouldBe (-1000)
    0.binaryPower(5) shouldBe 0
  }

  test("Testing of isPrimeFermatStrict") {
    2.isPrimeFermatStrict() shouldBe true
    561.isPrimeFermatStrict() shouldBe false
  }

  test("Testing of isPrimeFermatFast") {
    2.isPrimeFermatFast() shouldBe true
    561.isPrimeFermatFast() shouldBe false
  }

  test("Testing of isZuckerman") {
    3.isZuckerman shouldBe true
    212.isZuckerman shouldBe true
    10.isZuckerman shouldBe false
  }

  test("Testing of isHarshad") {
    3.isHarshad shouldBe true
    21.isHarshad shouldBe true
    11.isHarshad shouldBe false
  }

  test("Testing of the gcdExtended") {
    17.gcdExtendedWith(4) shouldBe (1, -4)
    (-5).gcdExtendedWith(4) shouldBe (2, 3)
  }
}
