<template>
  <div>
    <div class="pad30">
     <div class="">
       <div class="addWrap">
          <textarea class="rightText" placeholder="请输入" maxlength="200" @input="descInput" v-model="desc" />
            <span class="numberV">{{txtVal}}/500</span>
          
       </div>
       <ul>
         <li>
            <ImageUpload @getUpload='getUploadImg' :limit='limit'>
            </ImageUpload>
          </li>
       </ul>
     </div>
    </div>

    <div class="btn">
      <div class="btnText blue putHover" @click="cancel()">取消</div>
      <div class="btnText red putHover" @click="confirm()">确认</div>
    </div>
  </div>
</template>

<script>
import ImageUpload from '@/components/other/ImageUpload'
export default {
  name: 'addTalk',
  components:{
    ImageUpload
  },
  data () {
    return {
      limit:3,
      txtVal: 0,
      desc:"",
      UploadImg:''
      
    }
  },
  
  methods:{
    //图片上传组件向父组件方法
    getUploadImg(data){
      if(data){
        this.UploadImg = data.join(',')
      }
        
    },
    descInput(){
       this.txtVal = this.desc.length;
    },
    //取消
    cancel(){
      this.$router.go(-1);//返回上一层
    },
    //确定
    confirm(){
      
      if(!this.desc){
        this.$Message.error({
          content: '请添加二手信息描述',
          duration: 2000
        });
        return;
      }
      if(!this.UploadImg){
        this.$Message.error({
          content: '请上传图片',
          duration: 2000
        });
        return;
      }
      let $this = this;
      //authorName imgSrc
      this.$axios({
          method:'post',
          url:'forummessabe/add',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              content:$this.desc,
              img:this.UploadImg
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.$router.go(-1);//返回上一层
      })
       
    },
    //初始化数据接口
    loadData(){
      // usedbook/page
      this.$axios({
          method:'post',
          url:'/shareobject/page',
          data:{}
      }).then((res) =>{          //这里使用了ES6的语法
          this.hotList = res.data.data
      })
    },
    //查看列表详情
    toDetail:function(title,imgSrc,id,discribe,price,authorName,createDateTime,selectedShare){
      this.$router.push({
        path: '/shareDatail',
        //路由传参
        query: {
          title: title,
          selectedShare:selectedShare,
          authorName:authorName,
          createDateTime:createDateTime,
          discribe:discribe,
          price:price,
          imgSrc:this.$axios.defaults.baseURL+imgSrc
          
        }
      })
    }
  },
  mounted(){
    this.loadData()
  }
  
}
</script>

<style scoped lang='less'>
.pad30{
  padding:  0 30px;
}
.addWrap{
  padding: 30px 15px;
  display: flex;
  align-items: flex-start;
  .title{
    width: 120px;
    height: 40px;
    font-size: 16px;
    text-align: right;
    margin-right:10px;
  }
  .rightText{
    flex: 1;
    border:gainsboro 1px solid;
    border-radius: 4px;
    padding: 15px 10px;
  }
}

.btn{
 display: flex;
 justify-content: center;
 align-items: center;
 margin: 0.6rem 0;
 .btnText{
   width: 120px;
   height: 40px;
   color: white;
   font-size: 16px;
   border-radius: 4px;
   margin: 0 30px;
   line-height: 40px;
   text-align: center;
   &.red{
     background: orange;
     border: 1px solid orange;
   }
   &.blue{
    background: white;
    color: #26a2ff;
    border: 1px solid gainsboro;
   }
 }
}

</style>
