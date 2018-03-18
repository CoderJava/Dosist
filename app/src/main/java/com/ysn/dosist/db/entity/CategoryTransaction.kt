package com.ysn.dosist.db.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by yudisetiawan on 3/18/18.
 */
@Entity
class CategoryTransaction(
        @Id var id: Long = 0,
        var name: String = "",
        var description: String = ""
)