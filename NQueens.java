    /* 把这个想成填位置？有点像DFS诶
     * 首先NQueens可以表示成一维数组，然后记得判断对角线是不是可以的，那判断方法是|i1-i2|!=|a[i1]-a[i2]|
     * 只能一位一位往里面填数字，填下一位的时候判断是不是valid的，再往下做，类似于往n个位置里面填数字
     * recursive可以是记录下上一个填到了哪个index，然后接着填
     * 其实recursive有点像，比如你填4个，填到第四个，得到一个结果，return，然后再返回上一层，填下一个可能的第四个。。。依次类推，然后改第三层的数，再填第四层。。。。
     */
