package jp.co.who.json.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object SampleParser {

  import JacksonImplicits._

  // ObjectMapper の設定
  implicit val objectMapper: ObjectMapper = new ObjectMapper()
  objectMapper.registerModule(DefaultScalaModule)

  def main(args: Array[String]): Unit = {

    val bar = Person("Bar Smith")
    val foo = Person("Foo Smith", Set(bar))

    // JSON 文字列にシリアライズ
    val fooJson = foo.toJsonString
    println(fooJson)
    // 出力: {"name":"Foo Smith","children":[{"name":"Bar Smith","children":[]}]}

    // JSON 文字列からデシリアライズ
    val parsed = """{"name":"Hoge","children":[]}""".asJsonStringOf[Person]
    println(parsed)

  }

  case class Person(name: String, children: Set[Person] = Set())
}
