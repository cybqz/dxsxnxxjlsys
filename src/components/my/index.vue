<template>
  <div>
    <Tabs value="name1">
        <TabPane label="我的分享" name="name1">
          <div class="shareWrap pad30">
            <ul  class="hotShare">
              <li v-for='(item,i) in hotList' :key='i' @click="toDetail(item.title,decodeURI(item.imgSrc),item.id,item.discribe,item.price,item.authorName,item.createDateTime,selectedShare)">
                <div class="hotleft">
                    <img :src="$axios.defaults.baseURL+item.imgSrc">
                </div>
                <div class="hotright">
                  <div class="title">{{item.title}}</div>
                  <div class="discrib">
                    <div class="discribeText">
                      {{item.discribe}}
                    </div>
                    <div class="delete" @click="deleteShare(item.id)">
                      <img src="@/assets/images/delete.png">
                    </div>
                  </div>
                  <div class="time"><span>{{item.createDateTime}}</span> 发布</div>
                  <div class="hotbottom">
                    <div class="price">价格：<span v-if="item.price !='面议'&& item.price !='赠送'" class="red">￥</span> <span class="red">{{item.price}}</span></div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </TabPane>
        <TabPane label="我的论坛" name="name2">
          <div v-for="(item,i) in dataList" :key='i'>
            <!-- 间隔线 -->
            <div class="line10"></div>
            
            <div class="containTop pad30">
              <img src="@/assets/images/touxiang1.png">
              <div class="textCenter">
                <div class="name">{{item.name}}</div>
                <div class="time">发布时间：{{item.createDateTime}}</div>
              </div>
              <div class="delete" @click="deleteTalk(item.id)">
                <img src="@/assets/images/delete.png">
              </div>
            </div>
            <div class="containCenter pad30">
              <div v-if="!item.showAll" class="detailText">{{getText(item.content)}}
                <span @click="showAll(i)" v-if="item.content.length>100" class="blue"> 全文</span>
              </div>
              <div v-if="item.showAll" class="detailText">{{getText(item.content)}} 
                <span @click="showAll(i)" v-if="item.content.length>100" class="blue"> 收起</span>
              </div>
              <div class="detailImg" >
                <div v-for="(img,i) in item.imgList" :key="i">
                  <img :src="$axios.defaults.baseURL+img" >
                </div>
              </div>
            </div>
            <div class="containBottom pad30">
              <span  class="">
                <img v-if="item.praise" src="@/assets/images/zanY.png" >
                <img @click="praise(item.id)" v-else src="@/assets/images/zanN.png" >
              </span>
            </div>
          </div>
        </TabPane>
    </Tabs>
    
    
    
    
  </div>
</template>

<script>
export default {
  name: 'my',
  data () {
    return {
      hotList:[],
      dataList:[],
    }
  },
  components:{
  },
  methods:{
    
    //获取我的分享
    loadMyShare(){
      let $this = this
      this.$axios({
          method:'post',
          url:'shareobject/mypage',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                title:''
          })
      }).then((res) =>{          //这里使用了ES6的语法
          this.hotList = res.data.data
      })
    },
    
    //删除分享
    deleteShare(id){
      let $this = this
      this.$axios({
          method:'post',
          url:'/shareobject/delete',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                id:id
          })
      }).then((response) =>{          //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success({
              message: response.data.msg,
            });
            window.location.reload()
          }
      })
    },
    //删除论坛
    deleteTalk(id){
      let $this = this
      this.$axios({
          method:'post',
          url:'/forummessabe/delete',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                id:id
          })
      }).then((response) =>{          //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success({
              message: response.data.msg,
            });
            window.location.reload()
          }
      })
    },
    //获取我的论坛
    loadMyTalk(){
      let $this = this
      this.$axios({
          method:'post',
          url:'forummessabe/mypage',
          data:$this.qs.stringify({    //这里是发送给后台的数据
            title:''
          })
      }).then((res) =>{          //这里使用了ES6的语法
        this.dataList = res.data.data
        for(let i in this.dataList){
          this.$set(this.dataList[i], 'showAll', false);
          this.$set(this.dataList[i], 'imgList', this.dataList[i].img.split(','));
        }
        console.log(this.dataList)
      })
    },
    //点赞
    praise(id){
      let $this = this
      this.$axios({
          method:'post',
          url:'forumpraise/praise',
          data:$this.qs.stringify({    //这里是发送给后台的数据
                messageId:id
          })
      }).then((response) =>{   //这里使用了ES6的语法
          if(response.data.code =='200'){
            this.$Message.success({
              message: response.data.msg,
            });
            window.location.reload()
          }else{
             this.$Messag.error({
              message: response.data.msg,
            });
          }
      })
    },
    //过滤内容文字多余100字加省略号缩略
    getText(text){
      if(text.length>100){
        return text.substring(0,100)+'...';
      }else{
        return text;
      }
    },
    //是否展示全文
    showAll(index){
      this.dataList[i].showAll = !this.dataList[i].showAll
    },
    
  },
  mounted(){
    this.loadMyTalk();
    this.loadMyShare();
    
  }
  
}
</script>

<style scoped lang = 'less'>
.discribeText{
  flex: 1;
}
.delete{
    width: 30px;
    display: flex;
    align-items: center;
    padding: 10px;
    img{
      width: 20px !important;
      height:20px !important;
      border-radius: 0 !important;
      margin-right: 0;
    }
  }
.containTop{
  display: flex;
  align-items: center;
  height:70px;
  padding: 0 60px;
  padding-top: 30px;
  img{
    width: 70px;
    height:70px;
    margin-right: 30px;
    border-radius: 50%;
  }
  
  .textCenter{
    flex: 1;
    text-align: left;
    .name{
      width: 100%;
      height: 40px;
      line-height: 40px;
      font-size: 14px;
    }
    .time{
      width: 100%;
      font-size: 14px;
      height: 40px;
      line-height: 40px;
      color: #909090;

    }
  }
  
}
.containCenter{
  padding-top: 30px;
  font-size: 16px;
  text-align: left;
  .blue{
    color: #26a2ff;
    font-size: 16px;
  }
  .detailImg{
    padding-top:15px;
    width: 100%;
    display: flex;
    div{
      flex: 1;
      margin:  10px;
      img{
        width: 100%;
        height: auto;
      }
    }
  }
  
}
.containBottom{
  margin: 0 30px;
  font-size:16px;
  height: 40px;
  line-height: 40pxx;
  color: #909090;
  text-align: right;
  border-top: ghostwhite 1[x] solid;
  span{
    display: inline-block;
    width: 25%;
    text-align: right;
    margin-left: 30px;
    img{
      height: auto;
      width:24px;
      vertical-align: text-bottom;
    }
  }
}

.hotShare{
  li{
    display: flex;
    padding: 30px;
    border-bottom: ghostwhite 2px solid;
  }
  .hotleft{
    width: 300px;
    margin-right: 30px;
      img{
        border-radius: 3px;
        width: 210px;
        height: 140px;
      }
  }
  .hotright{
    flex: 1;
    .title{
      height: 30px;
      line-height: 30px;
      text-align: center;
      font-size: 16px;
      font-weight:bold;
    }
    .discrib{
      height: 60px;
      padding: 20px 0;
      width: 100%;
      font-size: 14px;
      text-align: left;
      display: flex;
      color: #909090;
    }
    .time{
      text-align: left;
      height: 20px;
      line-height: 20px;
      font-size: 12px;
      color: #909090;
    }
    .hotbottom{
      height: 20px;
      line-height: 20px;
      font-size: 12px;
      display: flex;
      color: #909090;
      .price{
        flex: 1;
        text-align: left;
      }
      .detail{
        flex: 1;
        text-align: right;
      }
      .red{
        color: red;
        font-size: 14px;
      }
      .blue{
          color: #26a2ff;
          font-size: 14px;
      }
    }
  }
}
.line10{
  height: 10px;
  background: ghostwhite;
  margin-bottom:10px;
}
.pad30{
  padding: 0 30px;
}

</style>
