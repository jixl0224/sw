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
/*snsoft/sw/cd/SkipTodayListener.java*/
snsoft.sw.cd.SkipTodayListener=Xjs.extend(Xjs.table.DefaultListener,{
  _js$className_:"snsoft.sw.cd.SkipTodayListener",
    /*snsoft.sw.cd.SkipTodayListener.addTableNotify*/
    addTableNotify:function(table)
    {
        this.table = table;
    },
    /*snsoft.sw.cd.SkipTodayListener.dataLoaded*/
    dataLoaded:function(dataSet,e)
    {
        snsoft.sw.cd.SkipTodayListener.superclass.dataLoaded.call(this,dataSet,e);
    }
});
/*snsoft/sw/cd/SumValueAccess.java*/
snsoft.sw.cd.SumValueAccess=Xjs.extend(Xjs.table.ColumnValueAccess,{
  _js$className_:"snsoft.sw.cd.SumValueAccess",
    /*snsoft.sw.cd.SumValueAccess.getValue*/
    getValue:function(dataSet,column,row)
    {
        return dataSet.getValue(column.name,row);
    },
    /*snsoft.sw.cd.SumValueAccess.setValue*/
    setValue:function(dataSet,column,value,e)
    {
        dataSet.setValue(column.name,eval(value));
    }
});
