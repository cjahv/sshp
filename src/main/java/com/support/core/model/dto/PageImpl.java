package com.support.core.model.dto;

import com.support.core.model.entity.BaseEntityImpl;

/**
 * 分页数据对象
 */
public class PageImpl<T extends BaseEntityImpl> implements Page<T> {
//    private int draw;//请求次数计数器
//    private int start;//第一条数据的起始位置
//    private int length;//每页显示的条数
//    private Search search;//全局的搜索条件
//    private Order[] order;//排序
//    private Columns[] columns;//每列信息
//    private boolean autoCount = true;//自动统计总数
//    private boolean csv;
//
//    private Class<T> entityClass;
//    private PageResult<T> result = new PageResult<>();
//
//    private DataFilter<T> dataFilter = new DataFilterTolerate<>();
//
//    public PageImpl() {
//        this.start = 0;
//        this.length = 10;
//    }
//
//    @Override
//    public PageImpl<T> set(Class<T> entity) {
//        this.entityClass = entity;
//        return this;
//    }
//
//    @Override
//    public Class<T> getEntityClass() {
//        return entityClass;
//    }
//
//    @Override
//    public int getDraw() {
//        return draw;
//    }
//
//    @Override
//    public int getStart() {
//        return start;
//    }
//
//    @Override
//    public PageImpl<T> setStart(int start) {
//        this.start = start;
//        this.dataFilter.length(start, length);
//        return this;
//    }
//
//    @Override
//    public int getLength() {
//        return length;
//    }
//
//    @Override
//    public PageImpl<T> setLength(int length) {
//        this.length = length;
//        this.dataFilter.length(length);
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> dotCount() {
//        this.autoCount = false;
//        return this;
//    }
//
//    @Override
//    public Search getSearch() {
//        return search;
//    }
//
//    @Override
//    public Order[] getOrder() {
//        return order;
//    }
//
//    @Override
//    public Columns[] getColumns() {
//        return columns;
//    }
//
//    @Override
//    public PageImpl<T> setColumnsByKeys(String[] keys) {
//        if (keys.length != columns.length) throw new InsideException("重置列失败!长度不一致!");
//        for (int i = 0; i < keys.length; i++) {
//            columns[i].setData(keys[i]);
//        }
//        return this;
//    }
//
//    @Override
//    public void removeKey(String... keys) {
//        if (ArrayUtils.isEmpty(keys)) return;
//        columns = Arrays.stream(columns).filter(s -> !ArrayUtils.contains(keys, s.getData())).toArray(Columns[]::new);
//    }
//
//    public void addKey(String... keys) {
//        int length = columns.length + keys.length;
//        Columns[] columns1 = new Columns[length];
//        for (int i = 0; i < length; i++) {
//            if (i < columns.length) {
//                columns1[i] = columns[i];
//            } else {
//                columns1[i] = new Columns();
//                columns1[i].setData(keys[i - columns.length]);
//                columns1[i].setSearchable(false);
//                columns1[i].setOrderable(false);
//            }
//        }
//        columns = columns1;
//    }
//
//    @Override
//    public PageImpl<T> filter(String... keys) {
//        if (ArrayUtils.isNotEmpty(keys)) {
//            this.dataFilter.clearKeys();
//            this.dataFilter.keys(keys);
//        }
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> filter(String keys) {
//        if (StringUtils.isNotEmpty(keys)) filter(StringUtils.split(keys, ","));
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> filter(String keys, Filter... filters) {
//        this.dataFilter.keys(keys);
//        this.dataFilter.add(filters);
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> filter(Filter... filters) {
//        this.dataFilter.add(filters);
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> filter(org.hibernate.criterion.Order... orders) {
//        this.dataFilter.order(orders);
//        return this;
//    }
//
//    @Override
//    public PageImpl<T> filter(String keys, org.hibernate.criterion.Order... orders) {
//        this.dataFilter.keys(keys);
//        this.dataFilter.order(orders);
//        return this;
//    }
//
//    @Override
//    public DataFilter<T> filter() {
//        dataFilter.length(start, length);
//        dataFilter.set(entityClass);
//
//        List<String> keys = new ArrayList<>();
//        for (Columns column : columns) {
//            if (StringUtils.isEmpty(column.getData())) continue;
//            keys.add(column.getData());
//        }
//        dataFilter.keys(keys.toArray(new String[keys.size()]));
//
//        org.hibernate.criterion.Order[] orders = new org.hibernate.criterion.Order[this.order.length];
//        for (int i = 0; i < order.length; i++) {
//            if (columns[order[i].getColumn()].isOrderable()) {
//                if (order[i].getDir() == OrderEnum.asc) {
//                    orders[i] = org.hibernate.criterion.Order.asc(columns[order[i].getColumn()].getData());
//                } else if (order[i].getDir() == OrderEnum.desc) {
//                    orders[i] = org.hibernate.criterion.Order.desc(columns[order[i].getColumn()].getData());
//                }
//            }
//        }
//        dataFilter.order(orders);
//
//        String search = this.search == null ? null : this.search.getValue();
//
//        if (StringUtils.isEmpty(search)) {
//            for (Columns s : columns) {
//                if (StringUtils.isNotEmpty(s.getSearch().getValue())) {
//                    search = s.getSearch().getValue();
//                    for (Columns ss : columns) {
//                        if (s.getData().equals(ss.getData())) {
//                            ss.setSearchable(true);
//                        } else {
//                            ss.setSearchable(false);
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//
//        if (StringUtils.isNotEmpty(search)) {
//            List<Filter> filters = new ArrayList<>();
//            for (Columns column : columns) {
//                if (column.isSearchable() && StringUtils.isNotEmpty(column.getData())) {
//                    Method method = null;
//                    if (column.getData().indexOf('.') > 0) {
//                        String[] keys1 = StringUtils.split(column.getData(), '.');
//                        Class c = entityClass;
//                        for (String key : keys1) {
//                            method = ObjectReflexUtil.getGetMethod(key, c);
//                            c = method.getReturnType();
//                        }
//                    } else {
//                        method = ObjectReflexUtil.getGetMethod(column.getData(), entityClass);
//                    }
//                    assert method != null;
//                    Class returnClass = method.getReturnType();
//                    String[] values = StringUtils.split(search, ',');
//                    for (String value : values) {
//                        if (StringUtils.isEmpty(value)) continue;
//                        if (returnClass.equals(Date.class) && Pattern.matches("^(1[7-9]|2[0-9])\\d{2}(\\-(0?[1-9]|1[0-2]))?(\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|1\\d|2\\d|3[01]))?( \\d{1,2}(:\\d{1,2}){0,2})?$", value)) {
//                            filters.add(Filter.eqDate(column.getData(), value));
//                        } else if (returnClass.equals(String.class)) {
//                            filters.add(Filter.like(column.getData(), value));
//                        } else if (ObjectReflexUtil.isWrapClass(returnClass) && Pattern.matches("^[\\d\\.]+$", value)) {
//                            if (value.indexOf('.') > 0) {
//                                if (returnClass.equals(Double.class))
//                                    filters.add(Filter.eq(column.getData(), Double.parseDouble(value)));
//                                else if (returnClass.equals(Float.class))
//                                    filters.add(Filter.eq(column.getData(), Float.parseFloat(value)));
//                            } else {
//                                if (returnClass.equals(Long.class))
//                                    filters.add(Filter.eq(column.getData(), Long.parseLong(value)));
//                                else if (returnClass.equals(Integer.class))
//                                    filters.add(Filter.eq(column.getData(), Integer.parseInt(value)));
//                            }
//                        }
//                    }
//                }
//            }
//            if (filters.size() > 1) dataFilter.add(Filter.or(filters));
//            else if (filters.size() == 1) dataFilter.add(filters.get(0));
//        }
//        if (this.csv) {
//            dataFilter.all();
//        }
//        return dataFilter;
//    }
//
//    @Override
//    public PageResult<T> getResult(List<T> list) {
//        result.setDraw(this.getDraw());
//        result.setData(list);
//        return result;
//    }
//
//    @Override
//    public void setDraw(int draw) {
//        this.draw = draw;
//    }
//
//    @Override
//    public void setSearch(Search search) {
//        this.search = search;
//    }
//
//    @Override
//    public void setOrder(Order[] order) {
//        this.order = order;
//    }
//
//    @Override
//    public void setColumns(Columns[] columns) {
//        this.columns = columns;
//    }
//
////    @Override
////    public void setEntityClass(Class<T> entityClass) {
////        this.entityClass = entityClass;
////    }
//
//    @Override
//    public PageResult<T> getResult() {
//        return result;
//    }
//
////    @Override
////    public void setResult(PageResult<T> result) {
////        this.result = result;
////    }
//
//    @Override
//    public DataFilter<T> dataFilter() {
//        return dataFilter;
//    }
//
////    @Override
////    public void setDataFilter(DataFilter<T> dataFilter) {
////        this.dataFilter = dataFilter;
////    }
//
//    @Override
//    public boolean isAutoCount() {
//        return autoCount;
//    }
//
//    public boolean isCsv() {
//        return csv;
//    }
//
//    public void setCsv(boolean csv) {
//        this.csv = csv;
//    }
}
