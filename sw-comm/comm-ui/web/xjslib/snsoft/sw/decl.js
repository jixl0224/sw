Xjs.loadedXjs.push("snsoft/sw/decl");
Xjs.namespace("snsoft.sw.decl");
snsoft.sw.decl.YMSumListener=function(){};
Xjs.apply(snsoft.sw.decl.YMSumListener.prototype,{
dataLoaded:function(dataSet,e)
{
var rows=dataSet.getRows();
if(rows.length==0)
return;
var sumIdx=dataSet.columnAt("sum");
for(var i=0;i<rows.length;i++)
{
var row=rows[i],sum=0;
for(var c=0;c<dataSet.columns.length;c++)
{
if(dataSet.getColumn(c).name.indexOf("$CRCOL$")==0)
sum+=rows[i][c];
}
row[sumIdx]=sum;
}
dataSet.setSort([-(sumIdx+1)]);
dataSet.gotoRow(0);
var table=dataSet.getListener(Xjs.table.Table);
table.aggregate.reAgg();
}
});
