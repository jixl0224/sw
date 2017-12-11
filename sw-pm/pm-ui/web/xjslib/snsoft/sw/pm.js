Xjs.loadedXjs.push("snsoft/sw/pm");
Xjs.namespace("snsoft.sw.pm");
snsoft.sw.pm.ActorRender=function(){};
Xjs.apply(snsoft.sw.pm.ActorRender.prototype,{
cellRender:function(value,info)
{
var row=info.row,dataSet=info.cell.table.dataSet,cnt=0;
for(var i=1;i<dataSet.columnCount;i++)
{
if(dataSet.getValue(i,row)!=null)
cnt++;
}
var name=info.cell.valueMap[value];
return name+"["+cnt+"]";
}
});
snsoft.sw.pm.ViewBottomValue=function(){};
Xjs.apply(snsoft.sw.pm.ViewBottomValue.prototype,{
dataLoaded:function(dataSet,e)
{
var table=dataSet.getListener(Xjs.table.Table);
for(var i=0;i<dataSet.columnCount;i++)
{
var name=dataSet.getColumn(i).name;
if(name.indexOf("$CRCOL")!=0)
continue;
var num=0;
for(var r=0;r<dataSet.getRowCount();r++)
{
if(dataSet.getValue(i,r)!=null)
num++;
}
table.getColumn(name).setBottomValue("人数："+num);
}
}
});
snsoft.sw.pm.ViewRender=function(){};
Xjs.apply(snsoft.sw.pm.ViewRender.prototype,{
cellRender:function(value,cellInfo)
{
if(value==null)
return null;
var infos=value,sb="",total=0;
for(var i=0;i<infos.length;i++)
{
var info=infos[i];
if(sb.length>0)
sb+="\n";
sb+=info.bedate.format(2)+"->"+info.ledate.format(2);
var days=(info.ledate.getTime()-info.bedate.getTime())/(1000*60*60*24)+1;
if(days>0)
sb+=":"+days;
total+=days;
}
sb+="\n共计天数："+total;
return sb;
}
});
