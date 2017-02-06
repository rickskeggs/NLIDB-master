/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlidb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import nlidb.Query.AbstractQueryFactory;
import nlidb.Query.Query;
import nlidb.Query.QueryProducer;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;


/**
 *
 * @author rskeggs
 */
public class POSNLIDB 
{
    static String WHITESPACE = null;
    static String TAGGER = null;
    static String TOKENIZER = null;
    
    public POSNLIDB(String properties)
   {
       try
       {   
           //Reading properties file in Java example
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(properties);
     
            //loading properites from properties file
            props.load(fis);

            //reading property
            WHITESPACE = props.getProperty("grammar.whitespace");
            TAGGER = props.getProperty("models.taggermodel");
            TOKENIZER = props.getProperty("models.tokenizermodel");
            
       }
       catch(FileNotFoundException fnfe){ fnfe.printStackTrace(); }
       catch(IOException ioe){ ioe.printStackTrace(); }        
    }
    
    
    public Query badquery(String tags[], String tokens[]) throws FileNotFoundException, IOException
    {
        String sentence = "Which customer has the postcode CM13";
        
        InputStream is = new FileInputStream("classifiers/ner-phd-test.ser.gz");
        System.out.println("LOADING " + new ParserModel(is));
        ParserModel model = new ParserModel(is);
        
        Parser parser = ParserFactory.create(model);
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        Query sqlQuery =null;
        
        //ArrayList sqlQuery = new ArrayList();
        AbstractQueryFactory qFactory = QueryProducer.getFactory();
        for(int i=0; i<tags.length; i++)
        {
            System.out.println("TAGS " + tags[i] + " TOKENS " + tokens[i]);
            
        }
        return sqlQuery;
    }
    
    public Query getQuery(String tags[], String tokens[]) throws FileNotFoundException, IOException
    {               
        Query sqlQuery =null;
        
        AbstractQueryFactory qFactory = QueryProducer.getFactory();
        sqlQuery=qFactory.getQuery(tags, tokens);
        return sqlQuery;
    }
    
    public static void main(String[] args)
    {
        try
        {
            NLTokenizer nlt = new NLTokenizer();
            POSNLIDB nlidb = new POSNLIDB("C:/Users/rskeggs/Documents/NetBeansProjects/NLIDB-master/src/nlptraining/trainingdata.properties");

            nlt.strTokenModel = TOKENIZER;

            String tokens[] = nlt.tokenize("Which customer has the postcode CM13#2LR");

            POSTagger pos = new POSTagger(); 
            pos.setTokenModel(nlidb.TAGGER);
            String[] tags = pos.tagger(tokens);


            Query list = nlidb.getQuery(tags, tokens);

        }
        catch(Exception e){ e.printStackTrace(); }
            
    }    
}
