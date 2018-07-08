package cn.itcast.test;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class TestIndexSearch {


    @Test
    public void test()throws Exception{
        //创建分词器 应该与索引和文档创建的时候使用的分词器一致
        IKAnalyzer analyzer = new IKAnalyzer();
        //创建查询语法对象
        //第一个参数默认搜索域，若另指定了搜索域，执行指定的,无指定，走默认的
        QueryParser queryParser = new QueryParser("name", analyzer);
        Query query = queryParser.parse("java");
        //创建索引库的位置
        Directory dir = FSDirectory.open(new File("F:\\dic"));
        //开启流读取对象
        DirectoryReader reader = IndexReader.open(dir);
        //创建搜索对象
        IndexSearcher searcher = new IndexSearcher(reader);
        //开始查询 第一个参数为查询语法对象 第二个参数为分页参数 指定每页展示数据
        TopDocs docs = searcher.search(query, 2);
        //获取结果集
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        //遍历结果集
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取查询到的文档id
            int docID = scoreDoc.doc;
            Document document = reader.document(docID);
            System.out.println("===id====" + document.get("id"));
            System.out.println("===name====" + document.get("name"));
            System.out.println("===price====" + document.get("price"));
            System.out.println("===pic====" + document.get("pic"));
            //System.out.println("===desc====" + document.get("desc"));
            System.out.println("=================");
        }
    }
}
