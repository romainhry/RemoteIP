package ViewerApp;


/**
* ViewerApp/ViewerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from View.idl
* samedi 2 avril 2016 01 h 30 CEST
*/

public abstract class ViewerPOA extends org.omg.PortableServer.Servant
 implements ViewerApp.ViewerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("view", new java.lang.Integer (0));
    _methods.put ("mouseMove", new java.lang.Integer (1));
    _methods.put ("mouseClick", new java.lang.Integer (2));
    _methods.put ("mousePress", new java.lang.Integer (3));
    _methods.put ("mouseRelease", new java.lang.Integer (4));
    _methods.put ("mouseRightClick", new java.lang.Integer (5));
    _methods.put ("mouseRightPress", new java.lang.Integer (6));
    _methods.put ("mouseRightRelease", new java.lang.Integer (7));
    _methods.put ("keyPress", new java.lang.Integer (8));
    _methods.put ("keyClick", new java.lang.Integer (9));
    _methods.put ("keyRelease", new java.lang.Integer (10));
    _methods.put ("shutdown", new java.lang.Integer (11));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ViewerApp/Viewer/view
       {
         byte $result[] = null;
         $result = this.view ();
         out = $rh.createReply();
         ViewerApp.ViewerPackage.byteArrayHelper.write (out, $result);
         break;
       }

       case 1:  // ViewerApp/Viewer/mouseMove
       {
         int x = in.read_long ();
         int y = in.read_long ();
         this.mouseMove (x, y);
         out = $rh.createReply();
         break;
       }

       case 2:  // ViewerApp/Viewer/mouseClick
       {
         this.mouseClick ();
         out = $rh.createReply();
         break;
       }

       case 3:  // ViewerApp/Viewer/mousePress
       {
         this.mousePress ();
         out = $rh.createReply();
         break;
       }

       case 4:  // ViewerApp/Viewer/mouseRelease
       {
         this.mouseRelease ();
         out = $rh.createReply();
         break;
       }

       case 5:  // ViewerApp/Viewer/mouseRightClick
       {
         this.mouseRightClick ();
         out = $rh.createReply();
         break;
       }

       case 6:  // ViewerApp/Viewer/mouseRightPress
       {
         this.mouseRightPress ();
         out = $rh.createReply();
         break;
       }

       case 7:  // ViewerApp/Viewer/mouseRightRelease
       {
         this.mouseRightRelease ();
         out = $rh.createReply();
         break;
       }

       case 8:  // ViewerApp/Viewer/keyPress
       {
         int code = in.read_long ();
         this.keyPress (code);
         out = $rh.createReply();
         break;
       }

       case 9:  // ViewerApp/Viewer/keyClick
       {
         int code = in.read_long ();
         this.keyClick (code);
         out = $rh.createReply();
         break;
       }

       case 10:  // ViewerApp/Viewer/keyRelease
       {
         int code = in.read_long ();
         this.keyRelease (code);
         out = $rh.createReply();
         break;
       }

       case 11:  // ViewerApp/Viewer/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ViewerApp/Viewer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Viewer _this() 
  {
    return ViewerHelper.narrow(
    super._this_object());
  }

  public Viewer _this(org.omg.CORBA.ORB orb) 
  {
    return ViewerHelper.narrow(
    super._this_object(orb));
  }


} // class ViewerPOA