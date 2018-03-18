package com.ysn.dosist.db.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by yudisetiawan on 3/18/18.
 */
@Entity
class DetailTransaction (
        @Id var id: Long = 0,
        var subject: String = "",
        var description: String = "",
        var timestamp: Long = System.currentTimeMillis(),
        var amount: Long = 0,
        var idCategory: Long = 0,
        var type: String = ""
)