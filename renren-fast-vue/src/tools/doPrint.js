import { getLodop } from '@/tools/lodop.js' //这块就是引用的上面一大坨暴露出来的方法喽

const PrintAccount = (qrTitle, codetype, code) => {
  // 调用打印对象
  var LODOP = getLodop()
  // 打印页面配置
  LODOP.ADD_PRINT_TEXT(53,187,75,20,"科学家");
  LODOP.ADD_PRINT_TEXT(100,131,272,20,"地址：中国北京社会科学院附近东大街西胡同");
  LODOP.ADD_PRINT_TEXT(138,132,166,20,"电话：010-88811888");
  // LODOP.ADD_PRINT_TEXT(159, 11, 163, 42, qrAccount)
  // LODOP.SET_PRINT_STYLEA(0, 'FontSize', 11)
}

export { PrintAccount }
