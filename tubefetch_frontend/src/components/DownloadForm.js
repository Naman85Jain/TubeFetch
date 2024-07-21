import React, {useState} from "react"
import axios from "../api/axios"

function DownloadForm() {
  const [videoUrl, setVideoUrl] = useState("")
  const [downloadUrl, setDownloadUrl] = useState("")

  const handleDownload = async () => {
    try {
      const response = await axios.post("/download", {url: videoUrl})
      setDownloadUrl(response.data)
    } catch (error) {
      console.error("Error downloading video:", error)
    }
  }

  return (
    <div>
      <input
        type="text"
        value={videoUrl}
        onChange={(e) => setVideoUrl(e.target.value)}
        placeholder="Enter YouTube URL"
      />
      <button onClick={handleDownload}>Download</button>
      {downloadUrl && (
        <div>
          <a href={downloadUrl} download>
            Download Video
          </a>
        </div>
      )}
    </div>
  )
}

export default DownloadForm
