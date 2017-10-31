package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object CustomerSpending {

  case class CustomerOrder(customerId: Int, orderId: Int, amount: Float)

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "CustomerSpending")
    val file = sc.textFile(FileLoader.resolveFile(args, "customer-orders.csv"))

    val orderSummary = file.map { text =>
        val customerOrder = parse(text)
        (customerOrder.customerId, customerOrder.amount)
      }
      .reduceByKey(_+_)
      .map(_.swap)
      .sortByKey()

    orderSummary
      .collect()
      .foreach { case(amount, customerId) => println(s"$customerId : $amount")}
  }

  def parse(text: String): CustomerOrder = {
    val fields = text.split(",")
    CustomerOrder(fields(0).toInt, fields(1).toInt, fields(2).toFloat)
  }
}
