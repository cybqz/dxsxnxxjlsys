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
                    <div class="detail"><span class="blue">查看详情></span></div>
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
            Toast({
              message: response.data.msg,
            });
            // this.$router.go(0);
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
            Toast({
              message: response.data.msg,
            });
            this.$router.go(0);
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
            Toast({
              message: response.data.msg,
            });
            this.$router.go(0);
          }else{
             Toast({
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

.delete{
    width: 0.6rem;
    display: flex;
    align-items: center;
    padding: 0.1rem;
    img{
      width: 0.4rem;
      height:0.4rem;
      border-radius: 0;
      margin-right: 0;
    }
  }
.containTop{
  display: flex;
  align-items: center;
  height:1.5rem;
  padding-top: 0.6rem;
  img{
    width: 1.5rem;
    height:1.5rem;
    margin-right: 0.6rem;
    border-radius: 50%;
  }
  
  .textCenter{
    flex: 1;
    text-align: left;
    .name{
      width: 100%;
      height: 0.8rem;
      line-height: 0.8rem;
      font-size: 0.48rem;
    }
    .time{
      width: 100%;
      font-size: 0.28rem;
      height: 0.5rem;
      line-height: 0.5rem;
      color: #909090;

    }
  }
  
}
.containCenter{
  padding-top: 0.3rem;
  font-size: 0.32rem;
  text-align: left;
  .blue{
    color: #26a2ff;
    font-size: 0.34rem;
  }
  .detailImg{
    padding-top: 0.3rem;
    width: 100%;
    display: flex;
    div{
      flex: 1;
      margin:  0.2rem;
      img{
        width: 100%;
        height: auto;
      }
    }
  }
  
}
.containBottom{
  margin: 0 0.3rem;
  font-size: 0.28rem;
  height: 1rem;
  line-height: 1rem;
  color: #909090;
  text-align: right;
  border-top: ghostwhite 0.04rem solid;
  span{
    display: inline-block;
    width: 25%;
    text-align: right;
    margin-left: 0.3rem;
    img{
      height: auto;
      width:0.4rem;
      vertical-align: text-bottom;
    }
  }
}

.hotShare{
  li{
    display: flex;
    padding-top: 0.3rem;
    border-bottom: ghostwhite 0.03rem solid;
  }
  .hotleft{
    width: 3rem;
    margin-right: 0.3rem;
      img{
        border-radius: 0.04rem;
        width: 3.3rem;
        height: 2.2rem;
      }
  }
  .hotright{
    flex: 1;
    padding-left: 0.3rem;
    .title{
      height: 0.5rem;
      line-height: 0.5rem;
      text-align: center;
      font-size: 0.36rem;
      font-weight:bold;
    }
    .discrib{
      height: 0.8rem;
      padding: 0.1rem 0;
      width: 100%;
      font-size: 0.28rem;
      text-align: left;
      display: flex;
      color: #909090;
      .discribeText{
        flex: 1;
      }
    }
    .time{
      text-align: left;
      height: 0.4rem;
      line-height: 0.4rem;
      font-size: 0.24rem;
      color: #909090;
    }
    .hotbottom{
      height: 0.4rem;
      line-height: 0.4rem;
      font-size: 0.24rem;
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
        font-size: 0.28rem;
      }
      .blue{
          color: #26a2ff;
          font-size: 0.28rem;
      }
    }
  }
}
.tabmr{
  margin: 0 1.5rem;
}
.padTOP{
  padding-top: 0.3rem;
}
.QRcode{
  margin: 0.3rem;
  padding: 0.3rem;
  box-shadow: inset 0 0 0.03rem 0 gainsboro;
  border-radius: 0.1rem;
  display: flex;
  align-items: center;
  .left{
    flex: 1;
    text-align: left;
    font-size: 0.36rem;
    color: orange;
  }
  .right{
    flex: 1;
    text-align: right;
    img{
      width: 0.6rem;
      height:0.6rem;
    }
  }
}
.addIcon{
  img{
    width: 0.5rem;
    height:0.5rem;
    vertical-align: bottom;
  }
}
.line10{
  height: 0.1rem;
  background: ghostwhite;
  margin-bottom: 0.1rem;
}
.pad30{
  padding: 0 0.3rem;
}
.padTOP{
  padding-top: 0.3rem;
}
.mrTOP{
  margin-top: 0.6rem;
}
.topWrap{
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0.6rem 0.3rem;
  padding: 0.3rem ;
  box-shadow: inset 0 0 0.03rem 0 gainsboro;
  border-radius: 0.1rem;
  .left{
      width: 1.8rem;
      height:1.8rem;
      margin-right: 0.3rem;
     img{
      width: 1.8rem;
      height:1.8rem;
      border-radius: 50%;
    }
  }
  .right{
    flex: 1;
    .nameAndClass{
      height: 0.8rem;
      display: flex;
      align-items: center;
      .dengji{
        width: 0.8rem;
        height: 0.4rem;
        font-size: 0.32rem;
        font-weight: bold;
        color: orange;
        img{
          width: 0.4rem;
          height: 0.4rem;
        }
      }
      .name{
        text-align: left;
        flex: 1;
        font-size: 0.42rem;
      }
    }
    .discribe{
      height: 1rem;
      font-size: 0.32rem;
      font-weight: bold;
      text-align: left;
      img{
        width: 0.4rem;
        height: 0.4rem;
      }
    }
  }
}
.intresting{
  
  box-shadow: inset 0 0 0.03rem 0 gainsboro;
  border-radius: 0.1rem;
  margin: 0.3rem;
   text-align: left;
  .title{
    font-size: 0.36rem;
    height: 0.6rem;
    line-height: 0.6rem;
   
    color: orange;
  }
  .card{
    display: flex;
    align-items: center;
    flex-direction: row;
    flex-wrap: wrap;
    span{
      height: 0.4rem;
      line-height: 0.4rem;
      display: inline-block;
      background: gainsboro;
      font-size: 0.32rem;
      margin: 0.3rem;
      padding: 0.1rem;

    }
  }
}
.chooseTab{
  margin-top: 0.3rem;
  padding:0.3rem;
  display: flex;
  li{
    flex:1;
    margin: 0 0.2rem;
    div{
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 0.36rem;
      img{
        width: 0.8rem;
        height: auto;
      }
    }
  }
}
</style>
