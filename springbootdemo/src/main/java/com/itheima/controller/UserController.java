package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserMapperService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapperService userMapperService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * spring整合redis
     * 配置文件中配置redis的端口和ip---也可以配置redis集群
     * @param id
     * @return
     */
    @RequestMapping("/user/{id}")
    @ResponseBody
    public User findById(@PathVariable int id) throws IOException {
        //先从redis中取数据
        String json = redisTemplate.opsForValue().get("user" + id);
        //如果redis中部位不为null或者一个空串,则从redis中查数据
        if (json!=null&&"".equals(json)){
            //将json数据转换为字符串-->使用objectMapper转换(jackson下的)
            User user = objectMapper.readValue(json, User.class);
            return user;
        }
        //如果redis中没有数据则查询数据库
        User user = userService.findById(id);
        //并将数据缓存至redis中
        redisTemplate.opsForValue().set("user"+id,objectMapper.writeValueAsString(user));
        return user;
    }

    @RequestMapping("/user/page/list")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        //将数据装入视图
        model.addAttribute("userList",userList);
        //返回对应视图解析器
        return "user";
    }

    /**
     * springboot整合mybatis
     * 编写mybatis映射文件后，位置可以放在两个位置
     * 1：当前mapper接口同级目录下  2:resources的相同目录下(///形式添加目录)
     * 第二种无需解释，需要在springboot配置文件中配置mybatis的包扫描和别名映射(不必须)
     * 第一种  默认在java目录下只编译源文件，想要编译xml文件需要在pom文件中添加build配置
     *   还需要在springboot启动类中添加mapperscan注解扫描mybatis接口
     * @param id
     * @return
     */
    @RequestMapping("/user/m/{id}")
    @ResponseBody
    public User findUserById(@PathVariable int id){
        User user = userMapperService.findById(id);
        return user;
    }
}
