Xjs.loadedXjs.push("snsoft/sw/cd");
/*snsoft/sw/cd/LoadListener.java*/
Xjs.namespace("snsoft.sw.cd");
snsoft.sw.cd.LoadListener=Xjs.extend(Xjs.table.DefaultTableListener,{
  _js$className_:"snsoft.sw.cd.LoadListener",
    /*snsoft.sw.cd.LoadListener.addTableNotify*/
    addTableNotify:function(table)
    {
        this.table = table;
    },
    /*snsoft.sw.cd.LoadListener.oncmd_loadCards*/
    oncmd_loadCards:function(table)
    {
        if(table.dataSet.getRowCount() == 0)
        {
            this.loadCards();
        } else 
        {
            Xjs.ui.UIUtil.showYesNoDialog("询问","重新加载将会删除已经存在的数据，确认重新加载吗？",{func:this.onLoadCards,scorp:this},null);
        }
    },
    /*snsoft.sw.cd.LoadListener.onLoadCards*/
    onLoadCards:function(w,cmd)
    {
        if("yes" == cmd)
        {
            this.loadCards();
        }
    },
    /*snsoft.sw.cd.LoadListener.loadCards*/
    loadCards:function()
    {
        var lid = this.table.masterTable.dataSet.getValue("lid"),
            service = Xjs.RInvoke.newBean("SN-PM.LoadService");
        service.loadCards(lid);
        this.table.masterTable.dataSet.refreshDetail(this.table.dataSet,null);
    },
    /*snsoft.sw.cd.LoadListener.oncmd_calcCards*/
    oncmd_calcCards:function(table)
    {
        table.masterTable.saveChanges();
        var lid = table.masterTable.dataSet.getValue("lid"),
            service = Xjs.RInvoke.newBean("SN-PM.LoadService");
        service.calcCards(lid);
        table.masterTable.dataSet.refreshDetail(table.dataSet,null);
    }
});
/*snsoft/sw/cd/LoadService.java*/
Xjs.RInvoke.beansDef["SN-PM.LoadService"]={loadCards:{},calcCards:{}};
