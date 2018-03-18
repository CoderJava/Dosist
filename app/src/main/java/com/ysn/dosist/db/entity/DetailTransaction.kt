/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 10:49 AM
 */

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