package com.example.practicalfive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TextProcessorTest {

    private final TextProcessor processor = new TextProcessor();

    @Test
    public void validMunicipalitiesAreNormalised() {
        assertEquals("Beijing", processor.processMunicipality("beijing"));
        assertEquals("Beijing", processor.processMunicipality("BEIJING"));
        assertEquals("Beijing", processor.processMunicipality("Beijing"));
        assertEquals("Shanghai", processor.processMunicipality("SHANGHAI"));
        assertEquals("Tianjin", processor.processMunicipality("tianjin"));
        assertEquals("Chongqing", processor.processMunicipality("ChOnGqInG"));
    }

    @Test
    public void surroundingWhitespaceIsAccepted() {
        assertEquals("Beijing", processor.processMunicipality("  beijing  "));
    }

    @Test
    public void unsupportedNameIsInvalid() {
        assertNull(processor.processMunicipality("Guangzhou"));
    }

    @Test
    public void emptyOrWhitespaceIsInvalid() {
        assertNull(processor.processMunicipality(null));
        assertNull(processor.processMunicipality(""));
        assertNull(processor.processMunicipality("   "));
    }
}
