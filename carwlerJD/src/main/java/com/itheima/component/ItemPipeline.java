package com.itheima.component;

import com.itheima.dao.ItemDao;
import com.itheima.entity.Item;
import com.itheima.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ItemPipeline implements Pipeline{

    @Autowired
    private ItemService itemService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取从page传来的数据
        Item item = resultItems.get("item");
        //持久化到数据库
        if (item!=null){
            itemService.save(item);
        }
    }
}
