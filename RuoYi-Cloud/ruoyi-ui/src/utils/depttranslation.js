function depttranslation(row, column, deptOptions) {
  var datas = deptOptions;
  for (let i = 0; i < datas.length; i++) {
    if (datas[i].id == row.deptId) {
      return datas[i].label;
    }
    for (let j = 0; j < datas[i].children.length; j++) {
      if (datas[i].children[j].id == row.deptId) {
        return datas[i].children[j].label;
      }
      for (let k = 0; k < datas[i].children[j].children.length; k++) {
        if (datas[i].children[j].children[k].id == row.deptId) {
          return datas[i].children[j].children[k].label;
        }
        for (let o = 0; o < datas[i].children[j].children[k].children.length;
          o++) {
          if (datas[i].children[j].children[k].children[o].id == row.deptId) {
            return datas[i].children[j].children[k].children[o].label;
          }
        }
      }
    }
  }
  return "无"
}

export {
  depttranslation
}







