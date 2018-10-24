~(function (root, factory) {
    if (typeof define === "function" && define.amd) {
        define([], factory);
    } else if (typeof module === "object" && module.exports) {
        module.exports = factory();
    } else {
        root.NP = factory();
    }
}(this, function () {
    'use strict';
    /**
     * @ file 解决浮动运算问题，避免小数点后产生多位数和计算精度损失。
     * 问题示例：2.3 + 2.4 = 4.699999999999999，1.0 - 0.9 = 0.09999999999999998
     * NP.strip(0.09999999999999998); // =0.1
     * NP.plus(0.1, 0.2);             // =0.3 not 0.30000000000000004
     * NP.plus(2.3, 2.4);             // =4.7 not 4.699999999999999
     * NP.minus(1.0, 0.9);            // =0.1 not 0.09999999999999998
     * NP.times(3, 0.3);              // =0.9 not 0.8999999999999999
     * NP.times(0.362, 100);          // = 36.2, not 36.199999999999996
     * NP.divide(1.21, 1.1);          // =1.1 not 1.0999999999999999
     * NP.round(0.105, 2);            // =0.11 not 0.1
     */

    return {
        /**
         * 把错误的数据转正
         * strip(0.09999999999999998)=0.1
         */
        strip: function (num, precision = 12) {
            return +parseFloat(num.toPrecision(precision));
        },

        /**
         * Return digits length of a number
         * @ param {*number} num Input number
         */
        digitLength: function (num) {
            // Get digit length of e
            const eSplit = num.toString().split(/[eE]/);
            const len = (eSplit[0].split('.')[1] || '').length - (+(eSplit[1] || 0));
            return len > 0 ? len : 0;
        },

        /**
         * 精确加法
         */
        plus: function (num1, num2) {
            const baseNum = Math.pow(10, Math.max(this.digitLength(num1), this.digitLength(num2)));
            return (num1 * baseNum + num2 * baseNum) / baseNum;
        },

        /**
         * 精确减法
         */
        minus: function (num1, num2) {
            const baseNum = Math.pow(10, Math.max(this.digitLength(num1), this.digitLength(num2)));
            return (num1 * baseNum - num2 * baseNum) / baseNum;
        },
        /**
         * 精确乘法
         */
        times: function (num1, num2) {
            const num1Changed = Number(num1.toString().replace('.', ''));
            const num2Changed = Number(num2.toString().replace('.', ''));
            const baseNum = this.digitLength(num1) + this.digitLength(num2);
            return num1Changed * num2Changed / Math.pow(10, baseNum);
        },

        /**
         * 精确除法
         */
        divide: function (num1, num2) {
            const num1Changed = Number(num1.toString().replace('.', ''));
            const num2Changed = Number(num2.toString().replace('.', ''));
            return this.times((num1Changed / num2Changed), Math.pow(10, this.digitLength(num2) - this.digitLength(num1)));
        },

        /**
         * 四舍五入
         */
        round: function (num, ratio) {
            const base = Math.pow(10, ratio);
            return this.divide(Math.round(this.times(num, base)), base);
        }
    };
}));