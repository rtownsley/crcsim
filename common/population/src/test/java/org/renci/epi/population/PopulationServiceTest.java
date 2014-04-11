package org.renci.epi.population;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.renci.epi.util.DataLocator;

import org.apache.commons.lang3.StringUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/population-context.xml"})
public class PopulationServiceTest extends AbstractJUnit4SpringContextTests {

    private static Log logger = LogFactory.getLog (PopulationServiceTest.class); 
    private DataLocator _dataLocator = new DataLocator ();

    private static char inputSeparator   = '|';
    private static char outputSeparator  = '\t';
    private static String [] outputKeys  = {
	"sex", "SEXC", "INCOME", "FRISK", "VITALE", "AGE_G2",
	"FLU", "BLACK", "HISP", "OTHER", "FORMER",
	"NEVER", "ALONE", "MW", "SO", "WE", "USUAL",
        "NOINS", "PRIVA", "MEDICARE", "MEDICAID", "DUAL", "EDU", "id", "p_id", 
        "LAT", "LON", "stcotrbg", "zipcode", "MARRIED", "NEW_INCOME", "NEW_INCOME_CAT",
        "insNoneLt65", "insPrivaLt65", "insMedicareLt65", "insMedicaidLt65", "insDualLt65",
        "insNoneGte65", "insPrivaGte65", "insMedicareGte65", "insMedicaidGte65", "insDualGte65"
// debug , "insStatus", "insRandom","pumsp_rac1p", "people_race", "households_hh_income",
// debug (cont.) "people_age", "households_hh_size", "personKey", "outBlack", "outINCOME"
    };

    static {
	BasicConfigurator.configure ();
	Logger.getRootLogger().setLevel (Level.ERROR);
    }
    
    @Autowired
    protected PopulationService populationService;

    /*
    @Test
    public void testModelInputCompiler () throws Exception {
	Assert.assertTrue (this.populationService != null);
	this.populationService.compileModelInput (inputSeparator,
						  outputSeparator,
						  outputKeys);
    }
    */

    @Test
    public void testModelMultipleInputCompiler () throws Exception {
	Assert.assertTrue (this.populationService != null);
	this.populationService.compileMultipleModelInputs (inputSeparator,
                                                           outputSeparator,
                                                           outputKeys);
    }


    /*
    */
    @Test
    public void testGetPopulation () throws Exception {
	this.populationService.getPopulation (new String [] { "0" });
    }
}




/*
class DataLocator {
    private String _dataRoot =
	StringUtils.join (new String [] { "",
					  "dev",
					  "var",
					  "crcsim" },
	    File.separatorChar);

    // file generated by joining rti synthetic population and pums.
    public String getPreliminarySyntheticPopulation () {
	String [] parts = new String [] { _dataRoot, "generated", "sample.out" };
	return StringUtils.join (parts, File.separatorChar);
    }

    // input to and output files from the RTI ABM
    public String getModelOutputPath () {
	String [] parts = new String [] { _dataRoot, "model", "output" };
	return StringUtils.join (parts, File.separatorChar);
    }
    public String getModelInputFilePath () {
	String [] parts = new String [] { _dataRoot, "..", "crcsim", "model", "population.tsv" };
	return StringUtils.join (parts, File.separatorChar);
    }

    // geocoding census shapefile data
    public String getPolygonFile () {
	String [] parts = new String [] { _dataRoot, "census2010", "tl_2010_37_county10.shp" }; //"tl_2010_37_tabblock10.shp" };
	return StringUtils.join (parts, File.separatorChar);
    }

    // geocoded output data
    public String getGeocodedOutputPath () {
	String [] parts = new String [] { _dataRoot, "generated", "geocoded" };
	return StringUtils.join (parts, File.separatorChar);
    }

}
*/
