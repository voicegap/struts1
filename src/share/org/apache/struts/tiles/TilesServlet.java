/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/Attic/TilesServlet.java,v 1.2 2002/07/19 09:40:22 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2002/07/19 09:40:22 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.tiles;

import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.DefinitionsFactoryException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


/**
 * A simple servlet initializing and loading Tiles factory.
 * This servlet can be declared in web.xml, as well as all initialization
 * parameters available with the specified factory.
 * This servlet is intended to be used in application using Tiles without Struts.
 * @author Cedric Dumoulin
 */

public class TilesServlet extends HttpServlet
{

    /**
     * Initialize this servlet
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
  public void init() throws ServletException
  {
  log(  "Start Tiles initialization");
  System.out.println( "Start Tiles initialization" );
  super.init();

    // Create tiles definitions config object
  DefinitionsFactoryConfig factoryConfig = new DefinitionsFactoryConfig();
    // Get init parameters from web.xml files
  try
    {
    DefinitionsUtil.populateDefinitionsFactoryConfig(factoryConfig, getServletConfig());
    }
   catch(Exception ex)
    {
    throw new ServletException( "Can't populate DefinitionsFactoryConfig class from 'web.xml': " + ex.getMessage() );
    }

  try
    {
    System.out.println( "Try to load Tiles factory" );
    DefinitionsUtil.createDefinitionsFactory(getServletContext(), factoryConfig );
    log(  "Tiles Factory loaded");
    }
   catch( DefinitionsFactoryException ex )
    {
      log(  "Tiles Factory load fail !", ex);
    throw new ServletException( ex );
    }

  }


}
