<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <grammar>
      <productions>
        <xsl:apply-templates select="/grammar/productions"/>
      </productions>
    </grammar>
  </xsl:template>

  <!-- Finds an existing production with the specified terminal -->
  <xsl:template name="findproduction">
    <xsl:param name="alpha"/>
    <xsl:choose>
      <xsl:when test="$alpha!=''">
        <variable>
          <!-- one child, and alphabet is what we are looking for then copy the productions name -->
          <xsl:value-of select="$alpha"/>
        </variable>
      </xsl:when>
      <xsl:otherwise>
        <!-- create new one -->
        <terminal>
          <xsl:value-of select="."/>
        </terminal>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="createproduction">
    <xsl:param name="alpha"/>
    <xsl:param name="name"/>
    <xsl:param name="path"/>
    <production>
      <xsl:attribute name="name">
        <xsl:value-of select="$name"/>
      </xsl:attribute>
      <terminal>
        <xsl:value-of select="$alpha"/>
      </terminal>
    </production>
  </xsl:template>

  <xsl:template match="production">
    <xsl:choose>
      <!-- if only one child, can safely copy since no unit productions -->
      <xsl:when test="count(*)=1">
        <xsl:copy-of select="."/>
      </xsl:when>
      <xsl:otherwise>
        <production>
          <xsl:attribute name="name">
            <xsl:value-of select="@name"/>
          </xsl:attribute>
          <!-- This has > 1 child so must change all the terminals to variables -->
          <xsl:for-each select="*">
            <xsl:choose>
              <xsl:when test="name(.)='terminal'">
                <xsl:call-template name="findproduction">
                                        <!-- here is where we'd run into problems if we had an S->a|B
                                        or A->b|C etc. and previously created a new
                                        production to replace the S->a with an A->a.
                                        
                                        to fix, we'd have to maybe distinguish between the
                                        productions that we created for terminals and the
                                        productions that existed since the beginning. and only use
                                        the ones we created (since we'll 'know' that these are not
                                        going to change the grammar. 
                                        
                                        a plausible but maybe not so elegant fix: an additional pass
                                        prior to pass1 that removes all productions that result in a
                                        terminal. this would be a pre-pass to remove productions
                                        that could otherwise confuse this thing which effectively
                                        changes the grammar. -->
                  <xsl:with-param name="alpha" select="//grammar/productions/production[terminal=current()][count(*)=1]/@name"/>
                </xsl:call-template>
              </xsl:when>
              <xsl:otherwise>
                <xsl:copy-of select="."/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:for-each>
        </production>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
</xsl:stylesheet>