/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlidb;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelType;

/**
 *
 * @author rskeggs
 */
public class POSTraining
{
    protected String strModelFile;
    
    public POSModel trainPOSModel (String modelDataFile) throws FileNotFoundException, IOException
    {
        POSModel model = null;
        InputStream dataIn = new FileInputStream("training/"+modelDataFile);
            
        ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
        ObjectStream<POSSample> sampleStream = new WordTagSampleStream(lineStream);
        
        model = POSTaggerME.train("en", sampleStream, TrainingParameters.defaultParams(), null, null);
        System.out.println(model);
        return model;
    }
    public TokenNameFinderModel/*POSModel*/ trainModel(String modelDataFile)
    {
        
        TokenNameFinderModel tmodel = null;
        InputStream dataIn = null;
        try 
        {
            dataIn = new FileInputStream("training/"+modelDataFile);
            
            ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
            
            tmodel = NameFinderME.train("en", "model", sampleStream, null);
            System.out.println(tmodel);
        }
        catch (IOException e)
        {
            // Failed to read or parse training data, training failed
            e.printStackTrace();
        }
        finally
        {
            if (dataIn != null) 
            {
                try 
                {
                    dataIn.close();
                }
                catch (IOException e)
                {
                    // Not an issue, training already finished.
                    // The exception should be logged and investigated
                    // if part of a production system.
                    e.printStackTrace();
                }
            }
        }System.out.println(tmodel);
        return tmodel;
    }        
            
    public void saveNameFinderModel(TokenNameFinderModel model)
    {
        OutputStream modelOut = null;
        try
        {
            modelOut = new BufferedOutputStream(new FileOutputStream("classifiers/" + strModelFile));
            model.serialize(modelOut);
        }
        catch (IOException e)
        {
            // Failed to save model
            e.printStackTrace();
        }
        finally
        {
            if (modelOut != null)
            {
                try { modelOut.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
    
    public void savePOSModel(POSModel model)
    {
        OutputStream modelOut = null;
        try
        {
            modelOut = new BufferedOutputStream(new FileOutputStream("classifiers/" + strModelFile));
            model.serialize(modelOut);
        }
        catch (IOException e)
        {
            // Failed to save model
            e.printStackTrace();
        }
        finally
        {
            if (modelOut != null)
            {
                try { modelOut.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
    
    public String getModelFile()
    {
        return strModelFile;
    }
    
    public void setModelFile(String strModelFile)
    {
        this.strModelFile = strModelFile;
    }
    
    public static void main(String[] args)
    {
        try
        {
            POSTraining post = new POSTraining();
            post.setModelFile("en-pos-model.bin");
            //TokenNameFinderModel model = post.trainModel("en-pos-model.train");
            POSModel model = post.trainPOSModel("en-pos-model.train");
            post.savePOSModel(model);
            System.out.println(post.getModelFile());
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private InputStreamFactory InputStreamFactory(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
