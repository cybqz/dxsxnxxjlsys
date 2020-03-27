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
       <div class="addWrapRadio">
         <div class="title">
           价格：
         </div>
         <div class="rightChange">
            <mt-radio class="radioBOX"
              v-model="priceType"
              :options="options">
            </mt-radio>
           <div v-if="priceType == '3'" class="price">
              <input class="rightText" type="number" placeholder="请输入自定义的价格" v-model="priceNum">
           </div>
         </div>
       </div>
       <div class="addWrapRadio">
         <div class="title">
           类型：
         </div>
         <div class="rightChange">
            <mt-radio class="radioBOX"
              v-model="type"
              :options="options2">
            </mt-radio>
         </div>
       </div>
       <ul>
         <li>
            <input type="file" >
            <a class="add"><p>点击上传</p></a>
          </li>
       </ul>
     </div>
    </div>

    <div class="btn">
      <div class="btnText blue" @click="cancel()">取消</div>
      <div class="btnText red" @click="confirm()">确认</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'shareList',
  data () {
    return {
      title:'',
      describe:'',
      priceNum:"",
      type:'',
      priceType:'',
      options : [
        {
          label: '赠送',
          value: '1',
        },
        {
          label: '面议',
          value: '2'
        },
        {
          label: '自定义',
          value: '3'
        }
      ],
      options2:[
        {
          label: '书籍',
          value: '1',
        },
        {
          label: '资料',
          value: '2'
        },
        {
          label: '笔记',
          value: '3'
        },
        {
          label: '文具',
          value: '4'
        }
      ]
      
    }
  },
  components:{
  },
  methods:{
    //取消
    cancel(){
      this.$router.go(-1);//返回上一层
    },
    //确定
    confirm(){
      if(!this.title){
        Toast({
          message: '请添加二手信息名称',
          position: 'middle',
          duration: 2000
        });
        return;
      }
      if(!this.describe){
        Toast({
          message: '请添加二手信息描述',
          position: 'middle',
          duration: 2000
        });
        return;
      }
      if(!this.priceType){
        Toast({
          message: '请选择交易价格方式',
          position: 'middle',
          duration: 2000
        });
        return;
      }
      if(this.priceType == '3' && !this.priceNum){
        Toast({
          message: '请输入自定义的价格',
          position: 'middle',
          duration: 2000
        });
        return;
      }
      if(!this.type){
        Toast({
          message: '请选择分享类型',
          position: 'middle',
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
              flag:$this.type,
              discribe:$this.describe,
              price:$this.priceType == '3'?$this.priceNum:$this.priceType == '1'?'赠送':'面议'
          })
      }).then((res) =>{          //这里使用了ES6的语法
          // this.$router.go(-1);//返回上一层
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
  padding:  0 0.3rem;
}
.shadow{
  box-shadow: inset 0 0 0.03rem 0 gainsboro;
  border-radius: 0.1rem;
}
.addWrap{
  padding: 0.6rem 0.3rem;
  display: flex;
  align-items: flex-start;
  .title{
    width: 1.5rem;
    height: 0.8rem;
    font-size: 0.32rem;
    text-align: right;
    margin-right:0.2rem;
  }
  .rightText{
    flex: 1;
    border:gainsboro 0.02rem solid;
    border-radius: 0.06rem;
    padding: 0.3rem 0.1rem;
  }
}
.addWrapRadio{
  padding: 0.6rem 0.3rem;
  .title{
    width: 1.5rem;
    height: 0.8rem;
    font-size: 0.32rem;
    text-align: right;
  }
  .rightText{
    flex: 1;
    border:gainsboro 0.02rem solid;
    border-radius: 0.06rem;
    padding: 0.3rem 0.1rem;
  }
  .rightChange{
    padding-left:0.3rem ;
    .radioBOX{
      flex: 1;
      display: flex;
      
    }
  }
  .price{
    display: flex;
    padding: 0.3rem;
  }
}
.btn{
 display: flex;
 justify-content: center;
 align-items: center;
 margin: 0.6rem 0;
 .btnText{
   width: 2rem;
   height: 0.8rem;
   color: white;
   font-size: 0.32rem;
   border-radius: 0.08rem;
   margin: 0 0.6rem;
   line-height: 0.8rem;
   text-align: center;
   &.red{
     background: orange;
     border: 0.02rem solid orange;
   }
   &.blue{
    background: white;
    color: #26a2ff;
    border: 0.02rem solid gainsboro;
   }
 }
}

</style>
