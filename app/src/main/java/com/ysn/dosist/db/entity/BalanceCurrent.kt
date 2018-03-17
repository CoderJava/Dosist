package com.ysn.dosist.db.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Entity
class BalanceCurrent(
        @Id var id: Long = 0,
        var balance: Long = 0,
        var income: Long = 0,
        var expense: Long = 0
)
