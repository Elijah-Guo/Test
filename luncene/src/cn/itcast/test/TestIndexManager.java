package cn.itcast.test;

import cn.itcast.dao.Impl.BookDaoImpl;
import cn.itcast.poji.Book;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestIndexManager {


    @Test
    public void test()throws Exception{
        //获取数据
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.queryBookList();



        //创建文档集合
        ArrayList<Document> documentList = new ArrayList<>();

        //遍历数据，组件文档
        for (Book book : bookList) {
            Integer id = book.getId();
            String name = book.getName();
            String pic = book.getPic();
            Float price = book.getPrice();
            String desc = book.getDesc();

            //创建文档对象
            Document document = new Document();

            /**
             * id
             * 是否分词 否 id是一个整体不需要分词
             * 是否索引 是 需要根据主键查询
             * 是否存储 是 因为主键很重要, 以后跟数据库对应的数据关联就靠它
             */
            Field idFile=new StringField("id", String.valueOf(id), Field.Store.YES);
            /**
             * name
             * 是否分词 是 分词为了索引
             * 是否索引 是 索引为了查询
             * 是否存储 是 存储为了展示
             */

            Field namefile=new TextField("name", name, Field.Store.YES);

            /**
             * pic图片
             * 是否分词 否  是一个整体不需要分词
             * 是否索引 否  不需要根据图片查询
             * 是否存储 是  要在页面展示  所以需要存储
             * 不允许自动选择是否存储，所以就俩个参数
             */
            Field picfile=new StoredField("pic", pic);

            /**
             * price价格---是比较特殊的一个
             * 数字需要根据区间范围查询, 就必须分词, 必须索引, 必须存储,
             * 这是lucene底层算法规定. 是一个特例
             * 是否分词 是
             * 是否索引 是
             * 是否存储 是
             */
            Field pricefile=new FloatField("price", price, Field.Store.YES);

            /**
             * desc描述
             * 是否分词 是  需要创建索引
             * 是否索引 是  需要根据分词查询
             * 是否存储 否  不需要展示,因为内容过多 一般如果需要展示的话
             * 根据jdbc等语句利用id查询数据库，因为数据库会根据id自动创建数据库索引
             * 这样既节省索引空间 又能快速查询
             */
            Field descfile=new TextField("desc", desc, Field.Store.NO);

            //添加入文档对象中
            document.add(idFile);
            document.add(namefile);
            document.add(picfile);
            document.add(pricefile);
            document.add(descfile);

            //添加入文档集合中
            documentList.add(document);

            //到这一步，文档的域和文档都创建好了
        }

        //创建分词器，不使用 标准的分词器 利用对中文支持比较好的
        //中文分词器IKAnalyzer

        IKAnalyzer analyzer = new IKAnalyzer();

        //创建索引文件和文档文件存储位置
        Directory dir = FSDirectory.open(new File("F:\\dic"));
        //创建indexWriter的初始化对象写入对象
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //创建索引和文档的写入流对象
        IndexWriter writer = new IndexWriter(dir, config);
        //写入文档和索引
        for (Document document : documentList) {
            writer.addDocument(document);
        }

        //提交
        writer.commit();
    }
}
