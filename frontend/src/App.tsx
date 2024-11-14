import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Navbar from './components/ui/navbar'
import { createPojoGeneratorService } from './modules/pojo/application/service/pojoGeneratorService'
import { Payload } from './modules/pojo/domain/model/payload'
import { createPojoGeneratorRepositoryAdapter } from './modules/pojo/infrastructure/adapter/pojoGeneratorRepositoryAdapter'
import HomePage from './sections/home/HomePage'
import PojoHome from './sections/pojo/pojo-home'

const repository = createPojoGeneratorRepositoryAdapter()
const service = createPojoGeneratorService(repository)

const router = createBrowserRouter([
  {
    path: '/',
    element: <Navbar />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: '/pojo',
        element: <PojoHome />,
        action: async ({ request }) => {
          try {
            const formData = await request.formData()
            const entryValue = formData.get('payload')

            if (typeof entryValue !== 'string') {
              return new Response('Invalid payload format', { status: 400 })
            }

            const payload = JSON.parse(entryValue.toString()) as Payload
            const zipUrl = await service.generate(payload)

            const response = { success: true, zipUrl }

            return new Response(JSON.stringify(response), {
              status: 200,
              headers: { 'Content-Type': 'application/json' },
            })
          } catch (error) {
            console.error('Error processing action', error)
            const response = {
              success: false,
              error: 'Failed  to process the request',
            }
            return new Response(JSON.stringify(response), {
              status: 400,
              headers: { 'Content-Type': 'application/json' },
            })
          }
        },
      },
    ],
  },
  {
    path: '*',
    element: <h1>Not Found</h1>,
  },
])

function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  )
}

export default App
