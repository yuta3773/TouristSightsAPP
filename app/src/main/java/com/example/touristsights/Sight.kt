package com.example.touristsights

import android.content.res.Resources
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.BufferedReader
import java.io.InputStreamReader

//このクラスからJSONデータ生成、逆にJSONデータからクラスオブジェクト作れるようにする
@Serializable

class Sight(val name: String,
            val imageName: String,
            val description: String,
            val kind: String)

//JSONデータをSightクラスオブジェクトに変換
fun getSight(resources: Resources): List<String> {
    val assetManager = resources.assets

    //assets内JSONデータファイルを開き、テキストデータを取得
    val inputStream = assetManager.open("sights.json")

    //inputStream内のテキスト取り出す
    val bufferReader = BufferedReader(InputStreamReader(inputStream))

    //bufferReaderはテキストを読み込む為のクラスなので、readTextでファイル内容を文字列で取り出す
    val str: String = bufferReader.readText()

    //JSONクラスを使い、取得したJSONデータの文字列をSightクラスに変換
    //JSONクラスは引数にJsonConfiguration.Stableを渡し習得
    //parseでJSONデータをデコードしてオブジェクトにする。
    val obj = Json(JsonConfiguration.Stable).parse(Sight.serializer().list, str)
    return obj
}