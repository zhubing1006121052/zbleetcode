package com.qunar.sort;

/**
 * 归并排序
 */
public class MergeSort {


    public static void main(String[] args) {

    }

    /**
     *            [ 9 , 2 , 4 , 1 , 5 , 8 , 3 , 6 ,7]
     *分治排序   [2,9]  [1,4]  [5,8]  [3,6]  [7]
     *
     *分治排序  [1,2,4,9]  [3,5,6,8]  [7]
     *
     *分治排序 [1,2,3,4,5,6,8,9]  [7]
     *
     *分治排序 [1,2,3,4,5,6,7,8,9]
     * @param nums
     * @return
     */
    public static int[] mergeSort_diedai(int nums[]){

        while(){

        }

    }

    public static void mergeSort_digui(int nums[]){
        int tmp[] = new int[nums.length];//临时存放数据用的
        mergeSort_digui(nums,0,nums.length -1,tmp);
    }

    /**
     *
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    public static void mergeSort_digui(int nums[],int begin,int end,int tmp[]){

        if(begin < end){//当只有一个元素的时候结束递归
            int middle = (end + begin)/2;//分治  中间切分
            mergeSort_digui(nums,begin,middle,tmp);//左边
            mergeSort_digui(nums,middle + 1,end,tmp);//右边
            //[ 9 , 2 , 4 , 1 , 5 , 8 , 3 , 6 ,7]
            //递归中第一次进行到这里的是只有两个元素的时候,比如 [9,2] 因为再分就只有一个元素就不递归了
            //[9,2]  左边是[9] 右边是[2]  他们分别插入临时数组中，然后左边没放完继续放，最终覆盖到原始数组中[2,9]
            //这样一次次就是成了 [2,9]  [1,4]  [5,8]  [3,6]  [7]
            //....
            merge(nums,begin,middle+1,end,tmp);
        }

    }
    public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        int j = low,k = mid;  //左边序列和右边序列起始索引
        //左边和右边起始指针慢慢后移 谁小就将谁放到临时数组中
        //这块不用这个临时数组是不行的
        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        //最终不管左右两边是否有相同的元素，肯定有一边多出的元素没有放完
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while(j <= mid){
            tmp[i++] = arr[j++];
        }
        //如果右边没有放完就将右边没有放完的全部拷贝进tmp[]中
        while(k <= high){
            tmp[i++] = arr[k++];
        }
        //tmp排好序的数据是从下标0开始放的，但是要怎么还原到原来的数组的那一段位置中呢
        //我们知道是从low开始就行了
        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t];
        }
    }


}
