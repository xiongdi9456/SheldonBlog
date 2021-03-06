package com.sheldon.sheldonblog.dao;

import com.sheldon.sheldonblog.common.MyMapper;
import com.sheldon.sheldonblog.dao.provider.ArticleSqlProvider;
import com.sheldon.sheldonblog.entity.Article;
import com.sheldon.sheldonblog.entity.dto.form.ArticleSearchForm;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {

    String COLUMN_LIST = "article.id,title,introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified";

    @Select({
            "SELECT",
            COLUMN_LIST,
            "FROM",
            "article",
            "ORDER BY article.gmt_create DESC"
    })
    List<Article> getPostViewAllArticles();

    /**
     * 通过 tag id 查找文章
     *
     * @param id tag id
     *
     * @return 符合条件的文章
     */
    @Select({
            "SELECT",
            COLUMN_LIST,
            "FROM article",
            "INNER JOIN tag_article",
            "ON tag_article.article_id = article.id",
            "AND tag_article.tag_id=#{id}",
            "ORDER BY article.gmt_create DESC"
    })
    List<Article> getArticleListByTagId(Integer id);

    /**
     * 通过条件查找文章
     *
     * @param form 条件表单
     *
     * @return 符合条件的文章
     */
    @SelectProvider(type = ArticleSqlProvider.class, method = "getArticleByCondition")
    List<Article> getArticleListByCondition(ArticleSearchForm form);

}