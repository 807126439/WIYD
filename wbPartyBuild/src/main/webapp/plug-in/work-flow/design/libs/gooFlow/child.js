var jsondata = {
	"title" : "手机上网套餐办理",
	"nodes" : {
		"demo_node_1" : {
			"name" : "子树入口",
			"left" : 295,
			"top" : 0,
			"type" : "start round",
			"width" : 24,
			"height" : 24
		},
		"demo_node_2" : {
			"name" : "广告语",
			"left" : 256,
			"top" : 79,
			"type" : "state",
			"width" : 86,
			"height" : 24,
			"dep":"科技部"
		},
		"demo_node_3" : {
			"name" : "菜单",
			"left" : 265,
			"top" : 146,
			"type" : "join",
			"width" : 86,
			"height" : 24
		},
		"demo_node_7" : {
			"name" : "流量叠加包",
			"left" : 433,
			"top" : 149,
			"type" : "join",
			"width" : 109,
			"height" : 24
		},
		"demo_node_8" : {
			"name" : "手机上网套餐",
			"left" : 247,
			"top" : 246,
			"type" : "join",
			"width" : 117,
			"height" : 25
		},
		"demo_node_9" : {
			"name" : "夜猫套餐",
			"left" : 64,
			"top" : 253,
			"type" : "join",
			"width" : 86,
			"height" : 24
		},
		"demo_node_15" : {
			"name" : "套餐及上网流量剩余查询",
			"left" : 93,
			"top" : 140,
			"type" : "node",
			"width" : 119,
			"height" : 36
		},
		"demo_node_17" : {
			"name" : "10元夜猫",
			"left" : 106,
			"top" : 358,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_18" : {
			"name" : "5元夜猫",
			"left" : 2,
			"top" : 363,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_21" : {
			"name" : "2元叠加包",
			"left" : 591,
			"top" : 2,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_22" : {
			"name" : "5元叠加包",
			"left" : 598,
			"top" : 45,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_23" : {
			"name" : "10元叠加包",
			"left" : 603,
			"top" : 102,
			"type" : "node",
			"width" : 102,
			"height" : 24
		},
		"demo_node_24" : {
			"name" : "20元叠加包",
			"left" : 606,
			"top" : 147,
			"type" : "node",
			"width" : 106,
			"height" : 24
		},
		"demo_node_25" : {
			"name" : "30元叠加包",
			"left" : 597,
			"top" : 224,
			"type" : "node",
			"width" : 113,
			"height" : 24
		},
		"demo_node_26" : {
			"name" : "5元套餐",
			"left" : 87,
			"top" : 448,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_27" : {
			"name" : "8元套餐",
			"left" : 210,
			"top" : 405,
			"type" : "node",
			"width" : 86,
			"height" : 24
		},
		"demo_node_28" : {
			"name" : "10元套餐",
			"left" : 272,
			"top" : 451,
			"type" : "node",
			"width" : 91,
			"height" : 24
		},
		"demo_node_29" : {
			"name" : "20元套餐",
			"left" : 341,
			"top" : 412,
			"type" : "node",
			"width" : 91,
			"height" : 24
		},
		"demo_node_30" : {
			"name" : "30元套餐",
			"left" : 460,
			"top" : 437,
			"type" : "node",
			"width" : 91,
			"height" : 24
		},
		"demo_node_31" : {
			"name" : "50元套餐",
			"left" : 580,
			"top" : 464,
			"type" : "node",
			"width" : 92,
			"height" : 24
		},
		"demo_node_32" : {
			"name" : "GPRS功能办理",
			"left" : 683,
			"top" : 464,
			"type" : "node",
			"width" : 130,
			"height" : 24
		}
	},
	"lines" : {
		"demo_line_4" : {
			"type" : "sl",
			"from" : "demo_node_1",
			"to" : "demo_node_2",
			"name" : ""
		},
		"demo_line_5" : {
			"type" : "sl",
			"from" : "demo_node_2",
			"to" : "demo_node_3",
			"name" : ""
		},
		"demo_line_11" : {
			"type" : "sl",
			"from" : "demo_node_3",
			"to" : "demo_node_7",
			"name" : "2"
		},
		"demo_line_12" : {
			"type" : "sl",
			"from" : "demo_node_3",
			"to" : "demo_node_8",
			"name" : "1"
		},
		"demo_line_13" : {
			"type" : "sl",
			"from" : "demo_node_3",
			"to" : "demo_node_9",
			"name" : "3"
		},
		"demo_line_16" : {
			"type" : "sl",
			"from" : "demo_node_3",
			"to" : "demo_node_15",
			"name" : "4"
		},
		"demo_line_19" : {
			"type" : "sl",
			"from" : "demo_node_9",
			"to" : "demo_node_17",
			"name" : "2"
		},
		"demo_line_20" : {
			"type" : "sl",
			"from" : "demo_node_9",
			"to" : "demo_node_18",
			"name" : "1"
		},
		"demo_line_33" : {
			"type" : "sl",
			"from" : "demo_node_7",
			"to" : "demo_node_21",
			"name" : "1"
		},
		"demo_line_34" : {
			"type" : "sl",
			"from" : "demo_node_7",
			"to" : "demo_node_22",
			"name" : "2"
		},
		"demo_line_35" : {
			"type" : "sl",
			"from" : "demo_node_7",
			"to" : "demo_node_23",
			"name" : "3"
		},
		"demo_line_36" : {
			"type" : "sl",
			"from" : "demo_node_7",
			"to" : "demo_node_24",
			"name" : "4"
		},
		"demo_line_37" : {
			"type" : "sl",
			"from" : "demo_node_7",
			"to" : "demo_node_25",
			"name" : "5"
		},
		"demo_line_39" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_26",
			"name" : "1"
		},
		"demo_line_42" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_29",
			"name" : "4"
		},
		"demo_line_43" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_30",
			"name" : "5"
		},
		"demo_line_44" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_31",
			"name" : "6"
		},
		"demo_line_45" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_32",
			"name" : "7"
		},
		"demo_line_46" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_27",
			"name" : "2"
		},
		"demo_line_47" : {
			"type" : "sl",
			"from" : "demo_node_8",
			"to" : "demo_node_28",
			"name" : "3"
		}
	},
	"areas" : {},
	"initNum" : 48
};