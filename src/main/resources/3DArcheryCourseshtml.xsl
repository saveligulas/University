<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Archery Tournament</title>
            </head>
            <body>
                <h1>Turniertage und Gruppen</h1>
                <ul>
                    <xsl:for-each select="//course">
                        <li>
                            <a href="#{generate-id()}">
                                <xsl:value-of select="name"/>
                            </a>
                        </li>
                    </xsl:for-each>
                </ul>

                <xsl:apply-templates select="//course"/>

                <h1>Alphabetischer Index</h1>
                <ul>
                    <xsl:for-each select="//person">
                        <li>
                            <a href="#group_{@group_id}">
                                <xsl:value-of select="."/>
                            </a>
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="course">
        <h2>
            <xsl:value-of select="name"/>
        </h2>
        <p>
            <xsl:value-of select="location_description"/>
        </p>
        <h3>Teilnehmer:</h3>
        <ul>
            <xsl:for-each select="participants/person">
                <li id="group_{@group_id}">
                    <xsl:value-of select="."/>
                </li>
            </xsl:for-each>
        </ul>
    </xsl:template>

</xsl:stylesheet>