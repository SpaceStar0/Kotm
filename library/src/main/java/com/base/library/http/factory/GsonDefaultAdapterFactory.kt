package com.base.library.http.factory

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.`$Gson$Types`
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

/**
 * File GsonDefaultAdapterFactory
 * Created on 2022/5/15 12:59
 * Description:
 *
 * 处理null
 */
class GsonDefaultAdapterFactory : TypeAdapterFactory {
    override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        if (type.type == String::class.java) {
            return createStringAdapter()
        }
        if (type.rawType == List::class.java || type.rawType == Collection::class.java) {
            return createCollectionAdapter(type, gson)
        }
        return null
    }

    /**
     * null替换成空List
     */
    private fun <T : Any> createCollectionAdapter(
        type: TypeToken<T>,
        gson: Gson
    ): TypeAdapter<T>? {
        val rawType = type.rawType
        if (!Collection::class.java.isAssignableFrom(rawType)) {
            return null
        }

        val elementType: Type = `$Gson$Types`.getCollectionElementType(type.type, rawType)
        val elementTypeAdapter: TypeAdapter<Any> =
            gson.getAdapter(TypeToken.get(elementType)) as TypeAdapter<Any>

        return object : TypeAdapter<Collection<Any>>() {
            override fun write(writer: JsonWriter, value: Collection<Any>?) {
                writer.beginArray()
                value?.forEach {
                    elementTypeAdapter.write(writer, it)
                }
                writer.endArray()
            }

            override fun read(reader: JsonReader): Collection<Any> {
                val list = mutableListOf<Any>()
                // null替换为空list
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull()
                    return list
                }
                reader.beginArray()
                while (reader.hasNext()) {
                    val element = elementTypeAdapter.read(reader)
                    list.add(element)
                }
                reader.endArray()
                return list
            }

        } as TypeAdapter<T>
    }

    /**
     * null 替换成空字符串
     */
    private fun <T : Any> createStringAdapter(): TypeAdapter<T> {
        return object : TypeAdapter<String>() {
            override fun write(writer: JsonWriter, value: String?) {
                if (value == null) {
                    writer.value("")
                } else {
                    writer.value(value)
                }
            }

            override fun read(reader: JsonReader): String {
                // null替换为""
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull()
                    return ""
                }
                return reader.nextString()
            }

        } as TypeAdapter<T>
    }
}