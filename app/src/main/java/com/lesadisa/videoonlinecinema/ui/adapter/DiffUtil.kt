package com.lesadisa.videoonlinecinema.ui.adapter

class DiffUtil {
}
//https://startandroid.ru/ru/blog/504-primer-ispolzovanija-android-diffutil.html
/*public class ProductDiffUtilCallback extends DiffUtil.Callback {

    private final List<Product> oldList;
    private final List<Product> newList;

    public ProductDiffUtilCallback(List<Product> oldList, List<Product> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Product oldProduct = oldList.get(oldItemPosition);
        Product newProduct = newList.get(newItemPosition);
        return oldProduct.getId() == newProduct.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Product oldProduct = oldList.get(oldItemPosition);
        Product newProduct = newList.get(newItemPosition);
        return oldProduct.getName().equals(newProduct.getName())
                && oldProduct.getPrice() == newProduct.getPrice();
    }
}*/
