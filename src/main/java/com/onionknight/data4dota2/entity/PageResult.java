package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 9:42 2019/2/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
import java.io.Serializable;
import java.util.List;

public class PageResult<T>  {
    private long total;//总记录数
    private List<T> rows;//当前页结果

    public PageResult(){
        super();
    }
    public PageResult(long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
