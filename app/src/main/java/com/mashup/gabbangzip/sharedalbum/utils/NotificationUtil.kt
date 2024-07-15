package com.mashup.gabbangzip.sharedalbum.utils

import java.security.SecureRandom

object NotificationUtil {
    private val usedIdSet: HashSet<Int> = HashSet()
    private val random: SecureRandom = SecureRandom()

    fun getNotificationId(): Int {
        return if (usedIdSet.size >= Integer.MAX_VALUE) {
            usedIdSet.clear()
            usedIdSet.add(0)
            0
        } else {
            generateSequence { random.nextInt(Integer.MAX_VALUE) }
                .first { it !in usedIdSet }
                .also { usedIdSet.add(it) }
        }
    }
}
