window.CardReader = {
    inputValue: '',
    sendFun: null,
    timeDate: null,
    active: false,
    allowLength: [12, 17, 18, 20, 21, 22, 23, 30,58],
    keyCodeMap: {
        189: '-',
        187: '+',
        16: ''
    },
    init: function (fun, allowLength = [], keyCodeMap = {}) {
        this.sendFun = fun
        if (allowLength == null) {
            this.allowLength = []
        } else if (allowLength.length > 0) {
            this.allowLength == allowLength
        }
        Object.assign(this.keyCodeMap, keyCodeMap)
        this.timeDate = new Date()
        this.listen()
        this.timeListen()
        this.active = true
    },
    timeListen: function () {
        const date = new Date()
        const newT = date.getTime() - this.timeDate.getTime()
        this.timeDate = date
        return newT
    },
    listen: function () {
        document.onkeydown = (e) => {
            if (this.active) {
                if (this.timeListen() > 100) this.inputValue = ''
                let e1 = e || event || window.event || arguments.callee.caller.arguments[0]
                // console.log(e1.keyCode,String.fromCharCode(e1.keyCode))
                if (e1.keyCode == 17) return false // ctrl
                if (e1.keyCode == 13) {
                	// console.log(this.inputValue.length > 11 ,this.inputValue.length, this.allowLength,this.allowLength.indexOf(this.inputValue.length),this.allowLength.indexOf(this.inputValue.length) >= 0)
                    if (this.inputValue.length > 11 && this.allowLength.indexOf(this.inputValue.length) >= 0) {
                    	// console.log("end!")
                    	this.sendFun(this.inputValue)
                    } else{
                    	this.sendFun(null)
                    }
                    this.inputValue = ''
                } else {
                    if (this.keyCodeMap[e1.keyCode] != null) {
                        this.inputValue += this.keyCodeMap[e1.keyCode]
                    } else {
                        this.inputValue += String.fromCharCode(e1.keyCode)
                    }
//                    console.log(this.inputValue)
                }
            }
        }
    },
    start: function () {
        this.active = true
    },
    stop: function () {
        this.active = false
    }
}
