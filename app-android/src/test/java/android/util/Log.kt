/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */
package android.util

import java.io.PrintWriter
import java.io.StringWriter
import java.net.UnknownHostException

/**
 * This implementation avoids
 * "Method d,i,w,e in android.util.Log not mocked"
 * issues in Android jUnit tests
 */
object Log {

    @JvmStatic
    fun d(tag: String, msg: String): Int {
        println("DEBUG: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun i(tag: String, msg: String): Int {
        println("INFO: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun w(tag: String, msg: String): Int {
        println("WARN: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun e(tag: String, msg: String): Int {
        println("ERROR: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun e(tag: String, msg: String, tr: Throwable?): Int {
        println("ERROR: $tag: $msg\n${getStackTraceString(tr)}")
        return 0
    }

    @JvmStatic
    private fun getStackTraceString(tr: Throwable?): String {
        if (tr == null) return ""
        var throwable = tr
        while (throwable != null) {
            if (throwable is UnknownHostException) {
                return ""
            }
            throwable = throwable.cause
        }
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable?.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}
