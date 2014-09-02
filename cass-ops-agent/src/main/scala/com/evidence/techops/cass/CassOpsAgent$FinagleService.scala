/**
 * Generated by Scrooge
 *   version: 3.16.3
 *   rev: b8593c83072d94fc44feaa8d97940b9266d84ed0
 *   built at: 20140806-054445
 */
package com.evidence.techops.cass

import com.twitter.finagle.{Service => FinagleService}
import com.twitter.scrooge.{ThriftStruct, TReusableMemoryTransport}
import com.twitter.util.Future
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.TMemoryInputTransport
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, HashMap => mutable$HashMap}
import scala.collection.{Map, Set}


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class CassOpsAgent$FinagleService(
  iface: CassOpsAgent[Future],
  protocolFactory: TProtocolFactory
) extends FinagleService[Array[Byte], Array[Byte]] {
  import CassOpsAgent._

  private[this] val tlReusableBuffer = new ThreadLocal[TReusableMemoryTransport] {
    override def initialValue() = TReusableMemoryTransport(512)
  }

  private[this] def reusableBuffer: TReusableMemoryTransport = {
    val buf = tlReusableBuffer.get()
    buf.reset()
    buf
  }

  private[this] def resetBuffer(trans: TReusableMemoryTransport, maxCapacity: Int = 4096) {
    if (trans.currentCapacity > maxCapacity) {
      tlReusableBuffer.remove()
    }
  }

  protected val functionMap = new mutable$HashMap[String, (TProtocol, Int) => Future[Array[Byte]]]()

  protected def addFunction(name: String, f: (TProtocol, Int) => Future[Array[Byte]]) {
    functionMap(name) = f
  }

  protected def exception(name: String, seqid: Int, code: Int, message: String): Future[Array[Byte]] = {
    try {
      val x = new TApplicationException(code, message)
      val memoryBuffer = reusableBuffer
      try {
        val oprot = protocolFactory.getProtocol(memoryBuffer)

        oprot.writeMessageBegin(new TMessage(name, TMessageType.EXCEPTION, seqid))
        x.write(oprot)
        oprot.writeMessageEnd()
        oprot.getTransport().flush()
        Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()))
      } finally {
        resetBuffer(memoryBuffer)
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  protected def reply(name: String, seqid: Int, result: ThriftStruct): Future[Array[Byte]] = {
    try {
      val memoryBuffer = reusableBuffer
      try {
        val oprot = protocolFactory.getProtocol(memoryBuffer)

        oprot.writeMessageBegin(new TMessage(name, TMessageType.REPLY, seqid))
        result.write(oprot)
        oprot.writeMessageEnd()

        Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()))
      } finally {
        resetBuffer(memoryBuffer)
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  final def apply(request: Array[Byte]): Future[Array[Byte]] = {
    val inputTransport = new TMemoryInputTransport(request)
    val iprot = protocolFactory.getProtocol(inputTransport)

    try {
      val msg = iprot.readMessageBegin()
      val func = functionMap.get(msg.name)
      func match {
        case Some(fn) => 
          fn(iprot, msg.seqid)
        case _ =>
          TProtocolUtil.skip(iprot, TType.STRUCT)
          exception(msg.name, msg.seqid, TApplicationException.UNKNOWN_METHOD,
            "Invalid method name: '" + msg.name + "'")
      }      
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  // ---- end boilerplate.

  addFunction("getStatus", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = getStatus$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getStatus()
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("getStatus", seqid, getStatus$result(success = Some(value)))
      } rescue {
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getStatus", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("getColumnFamilyMetric", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = getColumnFamilyMetric$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getColumnFamilyMetric(args.keySpace, args.colFamily)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("getColumnFamilyMetric", seqid, getColumnFamilyMetric$result(success = Some(value)))
      } rescue {
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getColumnFamilyMetric", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("incrementalBackup", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = incrementalBackup$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.incrementalBackup(args.keySpace)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("incrementalBackup", seqid, incrementalBackup$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("incrementalBackup", seqid, incrementalBackup$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("incrementalBackup", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("snapshotBackup", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = snapshotBackup$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.snapshotBackup(args.keySpace)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("snapshotBackup", seqid, snapshotBackup$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("snapshotBackup", seqid, snapshotBackup$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("snapshotBackup", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("snapshotBackup2", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = snapshotBackup2$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.snapshotBackup2(args.keySpace)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("snapshotBackup2", seqid, snapshotBackup2$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("snapshotBackup2", seqid, snapshotBackup2$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("snapshotBackup2", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("commitLogBackup", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = commitLogBackup$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.commitLogBackup()
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("commitLogBackup", seqid, commitLogBackup$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("commitLogBackup", seqid, commitLogBackup$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("commitLogBackup", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("restoreBackup", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = restoreBackup$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.restoreBackup(args.keySpace, args.snapShotName, args.hostId)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Unit =>
        reply("restoreBackup", seqid, restoreBackup$result())
      } rescue {
        case e: BackupRestoreException => {
          reply("restoreBackup", seqid, restoreBackup$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("restoreBackup", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("csvToSsTableConv", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = csvToSsTableConv$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.csvToSsTableConv(args.csvFilePath, args.keySpace, args.colFamily, args.partitioner)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: String =>
        reply("csvToSsTableConv", seqid, csvToSsTableConv$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("csvToSsTableConv", seqid, csvToSsTableConv$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("csvToSsTableConv", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("ssTableImport", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = ssTableImport$args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.ssTableImport(args.ssTableFilePath, args.keySpace, args.colFamily)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Boolean =>
        reply("ssTableImport", seqid, ssTableImport$result(success = Some(value)))
      } rescue {
        case e: BackupRestoreException => {
          reply("ssTableImport", seqid, ssTableImport$result(ea = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("ssTableImport", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
}