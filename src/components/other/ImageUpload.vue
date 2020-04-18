<template>
  <div>
      <div v-if="limit != imgList.length" class="uploadBtnWrap">
          <div class="uploadBtn shadow">
            <input ref="uploadInput"
                type="file"
                class='upinp'
                name="file"
                value=""
                accept="image/gif,image/jpeg,image/jpg,image/png"
                @change="selectImg($event)"/>
            <div  class="text">
                <img src="@/assets/images/uploadImg.png" alt="">
            </div>
        </div>
        <div v-if="limit>1" class="limit">
            还能上传{{limit-imgList.length}}张图片
        </div>
        <div v-if="imgList.length == 0 " class="limit">
            上传图片
        </div>
      </div>
      
      <ul v-if="imgList.length>0" class="imgListWrap">
          <li class="imgList shadow" v-for="(img,i) in imgList" :key="i">
            <img :src="$axios.defaults.baseURL+img" alt="">
            <span @click="deleteImg(i)" class="delete putHover">x</span>
          </li>
      </ul>
  </div>
</template>

<script>
export default {
  name: 'imageUpload',
  props: {
    limit:{
    type:Number ,
    default:1
    },
    
    uploadUrl:{
    type:String ,
    default:''
    }
  },
  data () {
    return {
        file:'',
        imgList:[]
    }
  },
  components:{
  },
  methods:{
    selectImg(e){
        let inputDOM = this.$refs.uploadInput;
　　　　this.file = inputDOM.files[0];// 通过DOM取文件数据
　　　　let size = Math.floor(this.file.size / 1024);//计算文件的大小　
　　　　let formData=new FormData();//new一个formData事件
　　　　formData.append("file",this.file); //将file属性添加到formData里
　　　　//此时formData就是我们要向后台传的参数了
        this.uploadFiles(formData);
    },
    //调用接口上传图片
    uploadFiles(file){
        let $this = this
        this.$axios({
          method:'post',
          url:'upload/image',
          data:file,
          headers:{
　　　　　　'Content-Type':'multipart/form-data' //值得注意的是，这个地方一定要把请求头更改一下
　　　　   }
        }).then((response) =>{   //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success(response.data.msg);
            this.imgList.push(response.data.data)
            this.$emit('getUpload',this.imgList)
          }else{
             this.$Message.error(response.data.msg);
          }
        })

    },
    //调用接口删除图片
    deleteImg(index){
        this.imgList.splice(index, 1);
        this.$emit('getUpload',this.imgList)
    }
  },
  mounted(){
  }
}
</script>

<style scoped lang='less'>

.limit{
    margin-left:30px;
    font-size: 18px;
    color: orange;
}
.uploadBtn{
    position: relative;
    
    width: 80px;
    height: 80px;
}
.text{
    position: absolute;
    top: 0;
    left: 0;
    width: 80px;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: center;
    img{
        width: 70px;
        height: 70px;
    }
}
.upinp{
    width: 80px;
    height: 80px;
    position:absolute;
    left:0px;
    right:0px;
    right:0px;
    bottom:0px;
    z-index:1;
    opacity:0;
}
.imgListWrap,.uploadBtnWrap{
    display: flex;
    align-items: center;
    flex-direction: row;
    flex-wrap: wrap;
    padding: 10px 70px;
}
.imgList{
    position: relative;
    width: 120px;
    height: 120px;
    display: flex;
    align-items: center;
    justify-content: center;
    img{
        width: 80px;
        height:80px;
    }
    .delete{
        position: absolute;
        right: 7px;
        top:3px;
        font-size: 14px;
    }
}
</style>
