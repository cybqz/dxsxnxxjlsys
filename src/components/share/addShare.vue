<template>
  <div>
    <div class="pad30">
     <div class="">
       <div class="addWrap">
         <div class="title">
           名称：
         </div>
         <input class="rightText" type="text" maxlength='15' placeholder="请添加二手信息名称" v-model="title">
       </div>
       <div class="addWrap">
         <div class="title">
           描述：
         </div>
         <textarea class="rightText"  rows='4' placeholder="请添加二手信息描述" v-model="describe"/>
       </div>
       <div class="addWrap">
         <div class="title">
           价格：
         </div>
         <input class="rightText" type="Number" placeholder="请输入自定义的价格" v-model="priceNum">
       </div>
       <div class="addWrapRadio">
         <div class="title">
           类型：
         </div>
         <div class="rightChange">
            <div v-for="(item,i) in options2" :key="i" @click="choose(i,item.choose)" class="radioBOX">
              <span :class="(imgShow == i+1 && item.choose)?'choosed':''">{{item.label}}</span>
              <img v-if="imgShow == i+1 && item.choose" src="@/assets/images/radioY.png" alt="">
              <img v-else src="@/assets/images/radioN.png" alt="">
              
            </div>
         </div>
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
  name: 'shareList',
  components:{
    ImageUpload
  },
  data () {
    return {
      limit:1,
      title:'',
      describe:'',
      priceNum:"",
      type:'',
      priceType:'',
      imgShow:1,
      options2:[
        {
          label: '书籍',
          value: '1',
          choose:false
        },
        {
          label: '资料',
          value: '2',
          choose:false
        },
        {
          label: '笔记',
          value: '3',
          choose:false
        },
        {
          label: '文具',
          value: '4',
          choose:false
        }
      ],
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
    //切换类型
    choose(i){
      this.imgShow =0;
      this.options2[i].choose = true
      this.imgShow = i+1;
      
    },
    //取消
    cancel(){
      this.$router.go(-1);//返回上一层
    },
    //确定
    confirm(){
      if(!this.title){
        this.$Message.error({
          content: '请添加二手信息名称',
          duration: 2000
        });
        return;
      }
      if(!this.describe){
        this.$Message.error({
          content: '请添加二手信息描述',
          duration: 2000
        });
        return;
      }
      if(!this.priceNum){
        this.$Message.error({
          content: '请输入自定义的价格',
          duration: 2000
        });
        return;
      }
      if(this.imgShow ==0){
        this.$Message.error({
          content: '请选择分享类型',
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
          url:'/shareobject/add',
          data:$this.qs.stringify({    //这里是发送给后台的数据
              title:$this.title,
              flag:$this.imgShow,
              discribe:$this.describe,
              price:$this.priceNum,
              imgSrc:this.UploadImg
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
  padding:  0 15px;
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
.addWrapRadio{
  padding: 30px 15px;
  .title{
    width: 120px;
    height: 40px;
    font-size: 16px;
    text-align: right;
  }
  .rightText{
    flex: 1;
    border:gainsboro 1px solid;
    border-radius: 4px;
    padding: 15px 10px;
  }
  .rightChange{
     display: flex;
     padding-left:45px ;
    .radioBOX{
      flex: 1;
      display: flex;
      font-size: 16px;
      height: 40px;
      align-items: center;
      text-align: left;
      padding: 0 40px;
      span{
        flex: 1;
        text-align: left;
        &.choosed{
          color: orange;
        }
      }
      img{
        width: 16px;
        height: 16px;
      }
      
    }
  }
  .price{
    display: flex;
    padding: 15px;
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
